#!/usr/bin/env bash

# Creates admin user and removing temporary keycloak user

set -e

KEYCLOAK_URL="http://localhost:8080"
KEYCLOAK_USER="keycloak"
KEYCLOAK_PASSWORD="password"

function post() {
    curl -s --fail \
        -H "Authorization: bearer ${accessToken}" \
        -H "Content-Type: application/json" \
        -d "${2}" \
        "${KEYCLOAK_URL}/${1}"
}

function put() {
    curl -s --fail \
        -X PUT \
        -H "Authorization: bearer ${accessToken}" \
        -H "Content-Type: application/json" \
        -d "${2}" \
        "${KEYCLOAK_URL}/${1}"
}

function get() {
    curl --fail --silent \
        -H "Authorization: bearer ${accessToken}" \
        -H "Content-Type: application/json" \
        "${KEYCLOAK_URL}/${1}"
}

function delete() {
    curl -s --fail \
        -X DELETE \
        -H "Authorization: bearer ${accessToken}" \
        -H "Content-Type: application/json" \
        "${KEYCLOAK_URL}/${1}"
}

#Admin Access Token
accessToken=$(
    curl -s --fail \
        -d "username=${KEYCLOAK_USER}" \
        -d "password=${KEYCLOAK_PASSWORD}" \
        -d "client_id=admin-cli" \
        -d "grant_type=password" \
        "${KEYCLOAK_URL}/realms/master/protocol/openid-connect/token" \
        | jq -r '.access_token'
)

echo "Creating initial admin user"

adminUser=$(jq -n "{
       username: \"admin\",
       enabled: true,
       firstName: \"Admin\",
       lastName: \"User\",
       email: \"admin@example.com\",
       credentials: [{
         type: \"password\",
         value: \"admin\",
         temporary: false
       }]
     }")

post "admin/realms/master/users" "${adminUser}"

echo "Setting up admin role"
newAdminUser=$(get "admin/realms/master/users?username=admin")
adminUserId=$(echo ${newAdminUser} | jq -r '.[].id')


masterRealmAdminRole=$(get "admin/realms/master/roles" | jq -r '
    .
    | map(
        select(.name == "admin")
    )
    | .[0]
')

masterRealmAdminRoleId=$(echo ${masterRealmAdminRole} | jq -r '.id')

adminUserRoleMapping=$(jq -n "[{
    id: \"${masterRealmAdminRoleId}\",
    name: \"admin\",
    description: \"\${role_admin}\"
}]")

post "admin/realms/master/users/$adminUserId/role-mappings/realm" "${adminUserRoleMapping}"

echo "Finished creating admin user"

oldAdminUser=$(get "admin/realms/master/users?username=keycloak")
oldAdminUserId=$(echo ${oldAdminUser} | jq -r '.[].id')

accessToken=$(
    curl -s --fail \
        -d "username=admin" \
        -d "password=admin" \
        -d "client_id=admin-cli" \
        -d "grant_type=password" \
        "${KEYCLOAK_URL}/realms/master/protocol/openid-connect/token" \
        | jq -r '.access_token'
)

deletedTempUser=$(delete "admin/realms/master/users/$oldAdminUserId")

echo $deletedTempUser
echo "Done"
