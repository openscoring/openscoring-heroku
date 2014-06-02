Openscoring application for the Heroku cloud platform

# Prerequisites #

* Heroku user account.
* [Heroku Toolbelt] (https://toolbelt.heroku.com/) or Heroku command-line client.

# Installation #

Check out the latest version of the application:
```
git clone https://github.com/jpmml/openscoring-heroku.git openscoring-heroku
```

Change the working directory to `openscoring-heroku` and log in to your Heroku user account:
```
heroku login
```

Create the Heroku application slot:
```
heroku create
```

Deploy the application to the Heroku application slot:
```
git push heroku master
```

Please refer to [Heroku documentation] (https://devcenter.heroku.com/categories/reference) for more specific dyno configuration options.

# Usage #

The base URL of the application is `http://<application slot name>.herokuapp.com/`. The installation can be verified by making a GET request to the model list endpoint at `http://<application slot name>.herokuapp.com/openscoring/model` (e.g. opening this address in a web browser). Upon success, the response should contain a JSON array of deployed model identifiers.

The application implements full [Openscoring REST API] (https://github.com/jpmml/openscoring). The access to HTTP methods `PUT` and `DELETE` that deal with model deployment and undeployment, respectively, is restricted to users with the "admin" role. The Openscoring application grants this role to all users that originate from the local network. Unfortunately, the Heroku cloud platform does not provide access (e.g. secure shell) to the application container.

The Openscoring application watches the contents of the auto-deploy directory `pmml` for changes. A model can be deployed by placing a new PMML file into that directory. Conversely, a model can be undeployed by removing an existing PMML file from that directory. In both cases, the changes must be first committed to the local repository and then pushed to the remote repository.

Deploying a file `DecisionTreeIris.pmml`:
```
cp ~/work/DecisionTreeIris.pmml pmml/DecisionTreeIris.pmml
git add pmml/DecisionTreeIris.pmml
git commit -m "Added a decision tree model for the iris dataset"
git push heroku master
```

The name of the file (without file name extension(s), if any) becomes the model identifier. Hence, the newly deployed model can be reached at `http://<application slot name>.herokuapp.com/openscoring/model/DecisionTreeIris`.

Undeploying a file `DecisionTreeIris.pmml`:
```
git rm pmml/DecisionTreeIris.pmml
git commit -m "Removed a decision tree model for the iris dataset"
git push heroku master
```

# License #

Openscoring is dual-licensed under the [GNU Affero General Public License (AGPL) version 3.0] (http://www.gnu.org/licenses/agpl-3.0.html) and a commercial license.

# Additional information #

Please contact [info@openscoring.io] (mailto:info@openscoring.io)