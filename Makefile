build:
    mvn clean package
build-docker: ## Build the application as a docker image
	mvn clean package -Pdocker