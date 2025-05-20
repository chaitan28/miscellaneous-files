# Azure CLI (az) Commands

This README provides commonly used Azure CLI (`az`) commands for managing Azure resources efficiently.

## Prerequisites
- Install the [Azure CLI](https://learn.microsoft.com/en-us/cli/azure/install-azure-cli)
- Authenticate using:
  ```sh
  az login
  ```

## 1. Authentication & Subscription
- Log in to Azure
  ```sh
  az login
  ```

- Display current subscription details
  ```sh
  az account show
  ```

- Show all available subscriptions
  ```sh
  az account list --output table
  ```

- Switch to a specific subscription
  ```sh
  az account set --subscription "<subscription-id>"
  ```

- Run this command to create a Service Principal:
   ```bash
   az ad sp create-for-rbac --name "JenkinsSP" --role contributor --scopes /subscriptions/<your-subscription-id> 
   ```
 - appId (clientId) → The unique ID for the Service Principal.
 - password (clientSecret) → The secret key for authentication.
 - tenant → The Azure Directory Tenant ID
 - in th azure console you see in Microsoft EntraID--> App registrations

- Modify your pipeline to use the Service Principal:
   ```bash
   az login --service-principal -u <clientId> -p <clientSecret> --tenant <tenantId>
   ```

### **Solution 3: Use Managed Identity (if running on an Azure VM)**
If your Jenkins server is an **Azure VM**, enable **Managed Identity**:
```bash
az login --identity
```
T

## 2. Resource Group Management
- Show all resource groups
  ```sh
  az group list --output table
  ```

- Create a new resource group
  ```sh
  az group create --name myResourceGroup --location eastus
  ```

- Remove a resource group
  ```sh
  az group delete --name myResourceGroup --yes --no-wait
  ```

## 3. Virtual Machine (VM) Management
- Show all VMs
  ```sh
  az vm list --output table
  ```

- Start a virtual machine
  ```sh
  az vm start --name myVM --resource-group myResourceGroup
  ```

- Stop a virtual machine
  ```sh
  az vm stop --name myVM --resource-group myResourceGroup
  ```

- Remove a virtual machine
  ```sh
  az vm delete --name myVM --resource-group myResourceGroup --yes
  ```

## 4. Azure Kubernetes Service (AKS)
- Show all AKS clusters
  ```sh
  az aks list --output table
  ```

- Deploy an AKS cluster
  ```sh
  az aks create --resource-group myResourceGroup --name myAKSCluster --node-count 2 --generate-ssh-keys
  ```

- Configure kubectl access to AKS
  ```sh
  az aks get-credentials --resource-group myResourceGroup --name myAKSCluster
  ```

- Remove an AKS cluster
  ```sh
  az aks delete --resource-group myResourceGroup --name myAKSCluster --yes --no-wait
  ```

## 5. Azure Container Registry (ACR)
- Show all container registries
  ```sh
  az acr list --output table
  ```

- Deploy an ACR instance
  ```sh
  az acr create --name myRegistry --resource-group myResourceGroup --sku Basic
  ```

- Authenticate to the ACR
  ```sh
  az acr login --name myRegistry
  ```

- Remove an ACR instance
  ```sh
  az acr delete --name myRegistry --resource-group myResourceGroup --yes
  ```

## 6. Storage Account
- Show all storage accounts
  ```sh
  az storage account list --output table
  ```

- Deploy a storage account
  ```sh
  az storage account create --name mystorageacct --resource-group myResourceGroup --sku Standard_LRS
  ```

- Remove a storage account
  ```sh
  az storage account delete --name mystorageacct --resource-group myResourceGroup --yes
  ```

## 7. Networking
- Show all Virtual Networks
  ```sh
  az network vnet list --output table
  ```

- Deploy a new VNet
  ```sh
  az network vnet create --name myVnet --resource-group myResourceGroup --address-prefix 10.0.0.0/16
  ```

- Show all Network Security Groups
  ```sh
  az network nsg list --output table
  ```

## 8. SQL Server & Database
- Deploy an Azure SQL Server
  ```sh
  az sql server create --name mySqlServer --resource-group myResourceGroup --location eastus --admin-user myAdmin --admin-password MyP@ssword123
  ```

- Deploy an Azure SQL database
  ```sh
  az sql db create --resource-group myResourceGroup --server mySqlServer --name myDatabase --service-objective S0
  ```

## Additional Resources
- [Azure CLI Documentation](https://learn.microsoft.com/en-us/cli/azure/)
- Run `az --help` for more command options.

