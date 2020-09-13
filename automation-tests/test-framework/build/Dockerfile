ARG NEWMAN_VERSION

FROM postman/newman:$NEWMAN_VERSION

LABEL description="newman alpine with html reporter based Docker image"

# Install newman-reporter-htmlextra.

RUN npm install --global newman-reporter-htmlextra
