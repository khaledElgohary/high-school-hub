<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang xml:lang>
<head>
  <meta charset="utf-8" />
  <meta name="generator" content="pandoc" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
  <style>
</style>
</head>
<body>
<nav id="TOC" role="doc-toc">
<ul>
<li><a href="#iteration-2-worksheet" id="toc-iteration-2-worksheet">Iteration 2 Worksheet</a>
<ul>
<li><a href="#paying-off-technical-debt" id="toc-paying-off-technical-debt">Paying off technical debt</a></li>
<li><a href="#solid" id="toc-solid">SOLID</a></li>
<li><a href="#retrospective" id="toc-retrospective">Retrospective</a></li>
<li><a href="#design-patterns" id="toc-design-patterns">Design
patterns</a></li>
<li><a href="#iteration-1-feedback-fixes" id="toc-iteration-1-feedback-fixes">Iteration 1 Feedback fixes</a></li>
</ul></li>
</ul>
</nav>
<h1 id="iteration-2-worksheet">Iteration 2 Worksheet</h1>
<h2 id="paying-off-technical-debt">Paying off technical debt</h2>
<blockquote>
<p>Show <strong>two</strong> instances of your group paying off
technical debt. For these two instances:</p>
<ul>
<li>Explain how your are paying off the technical debt.</li>
<li>Show commits, links to lines in your commit where you paid off
technical debt.</li>
<li>Classify the debt, and justify why you chose that classification
with 1-3 sentences.</li>
</ul>
<p>Example of how to link to a commit with line number - on the top of
the page, click “permalink”, which will link to the most recent commit
then, click the margin to choose a line:
https://code.cs.umanitoba.ca/3350-summer2023/sample-project/-/blob/8e38ae9c3084d62adc4ac5fafa3b87d7d862dc26/.gitignore#L7</p>
<p>Make sure the path it not to <code>main</code> or their
<code>develop</code> (or similar) branch, as those rapidly change.</p>
</blockquote>
<p>The first piece of technical debt that we paid off was replacing our stub database with a real one. This was technical debt
becuase every time you would restart the app, all the data would reset back to its defaults. Here is an example of the User persistence before the debt was paid off: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/d3210164e2fc73c1b798a37038ba4826fd1ea0c3/app/src/main/java/comp3350/highschoolhub/persistence/stubs/UserPersistenceStub.java#L12 Notice
that all the users are just being stored in an ArrayList which of course gets deleted after the app exits. Here is what we have now for the User persistence: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/d3210164e2fc73c1b798a37038ba4826fd1ea0c3/app/src/main/java/comp3350/highschoolhub/persistence/hsqldb/UserPersistenceHSQLDB.java#L19 The User persistence now just has
an instance variable for a String giving a path to the real database. This technical debt is classified as being Deliberate and Prudent. We purposely made the choice to use a fake database
to begin with so that we could implement more features for our Iteration 1 release of our application. Hence, we knew we would have to deal with the consequences later.</p>
<p>The second piece of technical debt that we paid off was tightly coupling our business layer objects to the presentation layer objects. This is technical debt because it makes it more difficult to switch objects out for each other if a new version of a class gets created. Here is an example of what was happening in the presentation layer before using interfaces: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/6c466ec0d6eb9fcdc47d861e784c73930b81a31c/app/src/main/java/comp3350/highschoolhub/presentation/Connections.java#L29
Notice that the Connections.java class was tightly coupled to ConnectionsManager.java. Now Connections.java can use any class that implements the IConnectionsManager interface: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/7f8f11e2eaa6e4ea6682186f3f89ad63178ec89d/app/src/main/java/comp3350/highschoolhub/presentation/Connections.java#L42
This technical debt is classified as Reckless and Deliberate. We all knew that we should not have tightly coupled our classes together; however, we tightly coupled everything together because we were under a time constraint.</p>
<h2 id="solid">SOLID</h2>
<blockquote>
<p>Find a SOLID violation in the project of group <code>n+1</code>
(group 13 does group 1). Open an issue in their project with the
violation, clearly explaining the SOLID violation - specifying the type,
provide a link to that issue. Be sure your links in the issues are to
<strong>specific commits</strong> (not to <code>main</code>, or
<code>develop</code> as those will be changed).</p>
<p>Provide a link to the issue you created here.</p>
</blockquote>
<p>Here is a link to the issue we created for Group 6: https://code.cs.umanitoba.ca/3350-summer2023/lakers-6/-/issues/56

Here is the description of the issue (This is also stated in the issue itself):
<blockquote>
Violation Type: Single Responsibility Principle

Description: UserHandler.java is handling password validation. This could be handled by its own class in the logic. UserHandler.java is mostly doing
user persistence access and password validation does not tie in well with that.

Link: https://code.cs.umanitoba.ca/3350-summer2023/lakers-6/-/blob/24f5c4f640bb1d618057edf6d32b6e6b707cf20d/app/src/main/java/comp3350/lakers/quizme/logic/UserHandler.java#L40</p>
</blockquote>
<h2 id="retrospective">Retrospective</h2>
<blockquote>
<p>Describe how the retrospective has changed the way you are doing your
project. Is there evidence of the change in estimating/committing/peer
review/timelines/testing? Provide those links and evidence here - or
explain why there is not evidence.</p>
</blockquote>
<p>The retrospective has improved the way that we create branches. In Iteration 1, we hardly 
ever pushed to main. The issue with this was that no one really knew which branch had
the most recent version of our application, so everytime someone needed to create a new branch,
we would have to determine which branch had the most recent version. In Iteration 2, we improved on this issue
by agreeing to push to main whenever all code in a branch is functional. This greatly improved our workflow
as whenever someone needed a new branch, they could just branch from main because they knew that main always had the
most recent copy of the fully working application.</p>
<p>This link: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/network/Iteration1Release
shows how we were creating branches in Iteration 1. Notice that even as we got closer to the end of the sprint, hardly any
merges were being made to main. The main branch is the one closest to the right side of the graph. Then looking at July 7-11 of the current 
branching graph for main: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/network/main
You can see that more merges to main have been made compared to Iteration 1.</p>
<h2 id="design-patterns">Design patterns</h2>
<blockquote>
<p>Show links to your project where you use a well-known design pattern.
Which pattern is it? Provide links to the design pattern that you
used.</p>
<p><strong>Note</strong>: Though Dependency Injection is a programming
pattern, we would like to see a programming pattern other than
Dependency Injection.</p>
</blockquote>
<p>A well known design pattern that we are using in our project is the Singleton design pattern. The User, Request, and HighSchool Persistence 
objects are used in multiple different places in the application but only one instance of each exists for the entire project. Here is a link
to where the only instance of these objects are located: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/7f8f11e2eaa6e4ea6682186f3f89ad63178ec89d/app/src/main/java/comp3350/highschoolhub/application/Services.java#L25-53.</p>
<p>Here are a few more links to where these objects are being accessed: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/20293c1c57ffa569532da919e8afa66a33fb53a3/app/src/main/java/comp3350/highschoolhub/business/AccessHighSchools.java#L24 That one is from the AccessHighSchools class.
This one is from the AccessRequests class: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/20293c1c57ffa569532da919e8afa66a33fb53a3/app/src/main/java/comp3350/highschoolhub/business/AccessRequests.java#L18 and finally one from the AccessUsers class: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/7f8f11e2eaa6e4ea6682186f3f89ad63178ec89d/app/src/main/java/comp3350/highschoolhub/business/AccessUsers.java#L26. All of these object are in the logic layer but
whenever the logic layer object is created, it is using one instance of the persistence object throughout the entire project.</p>
<h2 id="iteration-1-feedback-fixes">Iteration 1 Feedback fixes</h2>
<blockquote>
<p>Provide a link to an issue opened by the grader.</p>
<p>Explain what the issue was, and why it was flagged. Explain what you
did to refactor or fix your code to address the issue. Provide links to
the commits where you fixed the issue.</p>
<p>No issues? Show 1 more piece of technical debt being paid off. Or
demonstrate how you are not adding debt by being prudent in your new
additions.</p>
</blockquote>
<p>We had no issues opened by the grader so we demonstrate on how we are being prudent with our new additions. In the ConnectionConfirmer class
we have a method which tells us whether two given users are connected. Right now, users accepting requests is not fully implemented; however, we still return false
so that all of our code that relies on that logic knows how to handle false being returned and we do not run into any issues where exceptions are thrown because our
existing code did not take that value into account. Here is the link to the method in that class: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/26a93b464d6d8c10a823ccdcf2ff52fb0ade1591/app/src/main/java/comp3350/highschoolhub/business/ConnectionConfirmer.java#L15-28</p>
</body>
</html>
