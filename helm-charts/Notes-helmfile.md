# Notes about HelmFile
------------------------
## Plugins

### Helm diff
This is a Helm plugin giving you a preview of what a helm upgrade would change. It basically generates a diff between the latest deployed version of a release and a helm upgrade --debug --dry-run. This can also be used to compare two revisions/versions of your helm release.
### helm secrets
This is one of the most well-known secrets management tools for Helm. 
--------------------------
## Commands

### helmfile apply
apply all resources from state file only when there are changes.
this command uses helm diff plugin to determine changes between current state and applying state if any changes are there then upgrade the helm resources otherwise not.
### helmfile sysc
sync all resources from state file (repos, releases and chart deps)
### To get all options with purpose run following command
helmfile -h
---------------------------
