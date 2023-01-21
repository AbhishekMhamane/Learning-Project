# Infrastructure as code
# Terraform 
It uses providers to manage all on different cloud platforms.
It uses Hashicorp Configuration Language (HCL)
it has 3 phases init,plan and apply

# Terraform commands

## terraform init 
## terraform plan
## terraform apply
## terraform destroy
## terraform show 
   we can use option --json to see in json format
## terraform output (shows output variables)
## terraform validate
## terraform fmt
## terraform providers
## terraform refresh
## terraform graph

# Ways to pass variables 
1. Using Variables.tf 
2. Using variable defination files such as terraform.tfvars
terraform apply -var-file variables.tfvars
3. Commond line flags
terraform apply -var "filename=demo.txt" -var "content=welcome"
4. Environment variables

