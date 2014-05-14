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

The application implements full [Openscoring REST API] (https://github.com/jpmml/openscoring). However, it must be noted that the application does not yet implement any authentication or authorization functionality. So, it's probably a good idea to keep its whereabouts secret ("security through obscurity").

# License #

Openscoring is dual-licensed under the [GNU Affero General Public License (AGPL) version 3.0] (http://www.gnu.org/licenses/agpl-3.0.html) and a commercial license.

# Additional information #

Please contact [info@openscoring.io] (mailto:info@openscoring.io)