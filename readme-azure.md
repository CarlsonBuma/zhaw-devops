# Azure Console
az --version
az login

## Check runtime - Webapp
az webapp list-runtimes --os linux

# Build Webapp
## Create Azure Group
az group create --name devops-helloworld --location westeurope

## Create Azure Plan
az appservice plan create --name devops-container --resource-group devops-helloworld --sku F1 --is-linux --location westeurope

## Build
az webapp up --name devops-carlson-hello-world --plan devops-container --resource-group devops-helloworld --runtime NODE:24-lts --sku F1 --location westeurope


# Build Docker Webapp
az group create --name devops-demo-container --location westeurope

az appservice plan create --name devops-demo-container-plan --resource-group devops-demo-container --sku F1 --is-linux --location westeurope

az webapp create --resource-group devops-demo-container --plan devops-demo-container-plan --name devops-demo-app-container --container-image carlsonpatrick/devops-demo-app

