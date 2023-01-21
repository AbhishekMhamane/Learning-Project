#--- Declared simple resource

# terraform {
#   required_providers {
#     local = {
#       source  = "hashicorp/local"
#       version = "2.3.0"
#     }
#   }
# }

# terraform {
#   required_providers {
#     aws = {
#       version = ">= 2.7.0"
#       source = "hashicorp/aws"
#     }
#   }
# }

resource "local_file" "demo-file" {
  filename = "demo.txt"
  content  = "We are in demo file"
}

# resource "aws_instance" "demo-instance"{
#   ami = "ami-676374623647"
#   instance_type = "t2.micro"
#   tags = {
#     Name = "demo-webserver"
#   }
# }

#--- Using variables 

# resource "local_file" "demo-file" {
#   filename = var.filename
#   content  = var.content["first"]
# }

# resource "local_file" "demo-file" {
#   filename = each.value
#   for_each = toset(var.filename-list)
#   content  = "We are in demo file"
# }

#--- dependency on another resource

# resource "local_file" "demo-file" {
#   filename = var.filename
#   content  = "My pet is ${random_pet.my-pet.id}"

#   # Optional, we can define dependencies explicitly 
#   # depends_on = [
#   #   random_pet.my-pet
#   # ]
# }

# resource "random_pet" "my-pet" {
#   prefix    = var.prefix[0]
#   separator = "."
#   length    = "1"
# }

#--- output variables

# output "pet-name" {
#   value = random_pet.my-pet.id
# }

#--- Manage life cycle

# resource "local_file" "demo-file" {
#   filename = var.filename
#   content  = var.content["first"]
#   file_permission = "777"

#   lifecycle {
#     create_before_destroy = true
#     # prevent_destroy = true
#     # ignore_changes = [
#     #   file_permission
#     # ]
#   }
# }

# resource "aws_instance" "demo-instance"{
#   ami = "ami-676374623647"
#   instance_type = "t2.micro"
#   tags = {
#     Name = "demo-webserver"
#   }

#   #  lifecycle {
#   #   create_before_destroy = true
#   #   prevent_destroy = true
#   #   ignore_changes = [
#   #     tags 
#   #   ]
#   # }
# }
