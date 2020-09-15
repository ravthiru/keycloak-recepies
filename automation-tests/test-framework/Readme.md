Test Framework based on newman base image and newman-reporter-htmlextra library for html report

### To build image 
Run the script ```build_image.sh``` which is present in the build directory. 

### Run Tests

Once the Docker image is create and available, you can use ```run_tests.sh``` to 
run test cases placed in tests folder as postman collection

### Test cases
Test cases are writen as postman collection and placed in tests directory

### Test Report
After running test cases, Report will be placed in tests directory
