# Using Heroku to deploy servlets and JSPs

This tutorial will show you how to deploy and develop a Heroku app that runs servlets and JSPs.
There are two activities covered in this tutorial: deployment will cover how to obtain a remote repository, making a copy of it in your own repository, persisting changes from your local instance to your remote one in GitHub and automatically deploy your app in Heroku, and development will cover how to build and run your app locally.

Check the currently deployed version: https://swe432tomcat.herokuapp.com

## Quick Reference
Use these commnads only if you already followed this tutorial and want a quick reminder of common tasks:

### Redeploying the app by pushing changes to the remote repo
```
git add . ; git commit -m "TODO: I really should explain these changes"; git push
```

### Rerunning the app locally after some edits
```
mvn package ; heroku local
```

## Create GitHub and Heroku accounts

You can create accounts for free in both platforms (do not provide any payment info).

### GitHub

Go to https://github.com/join and create your account.

### Heroku

Go to https://signup.heroku.com to create your account.

Optional: You can use the GitHub student package found at https://www.heroku.com/github-students. 

## Deployment: Create a Git repo(sitory) and and link it to a Heroku app 

If you have not installed Git before, you can get it here: https://git-scm.com/downloads.

Now, follow these steps to bring this repo into your Github account:

### 1. Get this repo locally in your machine:
This code contains all necessary boilerplate for supporting servlets and JSPs in a Heroku app:
```
git clone https://github.com/luminaxster/swe432tomcat.git
```

### 2. Create an empty repo in your Github:

a. Go to [Github](www.github.com), login into your account, select the "repositories" tab, click on "New". Once in the "Create a new repository" page: give a name to your repo, make it private and let other options default, and click on "create repository".

c. This will take you to your new repository's page. Copy the url to access your repo in the quick setup section.
It should look like this:

``` https://github.com/<your_username>/<newly_created_repo_name>.git```

We will use this URL in step 3.

### 3. Redirect the local repo to your own repo and save the changes:

Remember to replace the url from step 2 ( ``` https://github.com/<your_username>/<newly_created_repo_name>.git ``` ) with your own repo's url.

```
cd swe432tomcat
git init
git remote set-url origin "https://github.com/<your_username>/<newly_created_repo_name>.git"
git add .
git commit -m "Initial commit: cloned repo"
git push
```

### 4. Create a Heroku app

Go to https://dashboard.heroku.com/apps: click on "New" > "Create New App", provide a name, and click on "create app".

### 5. Link repo and deploy 

Once in your Heroku app web page, select the "deploy" tab:

 a. set the deploy method to "Github"
 
 b. authorize Heroku to access your GitHub repositories
 
 c. select the recently created one
 
 d. click on "connect"
 
 e. activate automatic deploys
 
 f. click on deploy the "master" branch (only this time so you can see the changes immediately)
 
 g. Once your deploy is processed, click on "View"
 
### Updating your repo and redeploying

You only have to push your changes on your repo and they will redeploy automatically.

## Development: Running your app locally

Before deploying your app in the Web, you normally program, debug and test your app locally. To do so, we will need Apache Maven to build your app and Heroku CLI to run it locally. 

#### Apache Maven installation

If you have not installed Apache Maven before, you can get the binaries here: https://maven.apache.org/download.cgi, and follow the instructions here: https://maven.apache.org/install.html.

Note: if you are using a Unix-like system (e.g MacOS, Linux), you need to open a terminal and add the path to Maven permanently in your bash profile:
```
vi ~/.bash_profile
```
And add the following line to this file so Maven is runnable from the terminal from now on:
```
export PATH=/opt/apache-maven-3.6.3/bin:$PATH
```
The line above assumes Maven's path is "/opt/apache-maven-3.6.3/bin", or that your Maven download has been moved there. You can use a different path too, make sure your path is reflected in this line.

For Windows machines, add Maven's path to the PATH propertty in the system's environment variables.

Important: Reopen your terminal for the changes to be reflected.

#### Heroku CLI installation
If you have not installed the Heroku CLI  before, you can get it here: https://devcenter.heroku.com/articles/heroku-cli.

For Windows machines, this repo's [Procfile](https://github.com/luminaxster/swe432tomcat/blob/master/Procfile) is set up for Unix-like machines, "sh" is the shell command in Unix . In Windows, replace the following line in the Procfile:
```
web: sh target/bin/webapp
```
 
for the following:
```
web: target\bin\webapp.bat
```

### Build and run your app
To run the app contained in your repo, go your repo's root folder, the "POM.xml" should be there, this file is the configuration Maven uses to build your app so Heroku can run it. You should perform the following commands each time you want to run the latest version of your app, make sure there are no errors after you run them. 

Run this command in your terminal to build the app:
```
mvn package
```

And this one to run it:

```
heroku local
```

You should be able to access your app at http://localhost:5000.

### Making changes: adding a new servlet

In your machine, place your servlet file in the ```src/main/servlet``` folder and add the servlet annotation so your app builder (via Maven) knows how to index it:
```
import javax.servlet.annotation.WebServlet;

@WebServlet( name = "servletName", urlPatterns = {"/servicePathName"} )
```
 
You can inspect the [example](https://github.com/luminaxster/swe432tomcat/blob/master/src/main/java/servlet/HelloServlet.java) in that folder for more details.

Now you can observe, debug or test your app locally by building your app (in terminal: ```mvn package```) and running it in your local server (in terminal: ```heroku local```). After that, ```localhost:5000/servicePathName``` (as in urlPatterns property from the annotation above) must be working.


Finally, once you are done making changes in your app and want to make it public, pushing your local changes to your repo will automatically deploy this version of the app in your Heroku hosting (described in the Deployment section).

## Important
After you are satisfied changing your code, remember they are still in your machine. You must push these changes to your github's web repo (remote), only then they will be visible to everybody. If you followed the steps linking your Heroku app with this repo, pushing changes in to your remote repo will redeploy your Heroku app.

## Sharing your repo with the TA
Your repo must be private at all times and for me to grade your code, please add me as a contributor. My username is luminaxster.

## Follow the original guide
For more details about how to create a Tomcat setup from scratch, go to the Dev Center guide on how to [Create a Java Web Application using Embedded Tomcat](https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat).

## Resources: 

https://kbroman.org/github_tutorial/pages/init.html  



# BathroomReview
# PersistenceJava
