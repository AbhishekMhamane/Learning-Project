# variables file contains all variables type
# if we not mention default values in variables.tf file it will ask at time of execution on terminal

# string
variable "filename" {
  default = "demo.txt"
  type    = string
}

# List
variable "filename-list" {
  type = list(string)
  default = [
    "demo1.txt",
    "demo2.txt"
  ]
}

# List 
variable "prefix" {
  default = ["Mr", "Mrs", "Sir"]
  type    = list(string)
}

# map
variable "content" {
  type = map(any)
  default = {
    "first"  = "Welcome"
    "second" = "Thank you"
  }
}

# Set 
variable "prefix-set" {
  default = ["Mr", "Mrs", "Sir"]
  type    = set(string)
}

# Object
variable "person" {
  type = object({
    name     = string
    age      = number
    has_bike = bool
  })

  default = {
    name     = "jack"
    age      = 25
    has_bike = true
  }
}

# tuples
# we can use any type of variables in tuples
variable "age" {
  type    = tuple([string, number])
  default = ["one", 1]
}
