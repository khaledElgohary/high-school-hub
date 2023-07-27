<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang xml:lang>
<head>
  <meta charset="utf-8" />
  <meta name="generator" content="pandoc" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
  <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
  <![endif]-->
</head>
<body>
<nav id="TOC" role="doc-toc">
<ul>
<li><a href="#iteration-3-worksheet" id="toc-iteration-3-worksheet">Iteration 3 Worksheet</a>
<ul>
<li><a href="#what-technical-debt-has-been-cleaned-up" id="toc-what-technical-debt-has-been-cleaned-up">What technical debt has
been cleaned up</a></li>
<li><a href="#what-technical-debt-did-you-leave" id="toc-what-technical-debt-did-you-leave">What technical debt did you
leave?</a></li>
<li><a href="#discuss-a-feature-or-user-story-that-was-cutre-prioritized" id="toc-discuss-a-feature-or-user-story-that-was-cutre-prioritized">Discuss
a Feature or User Story that was cut/re-prioritized</a></li>
<li><a href="#acceptance-testend-to-end" id="toc-acceptance-testend-to-end">Acceptance test/end-to-end</a></li>
<li><a href="#acceptance-test-untestable" id="toc-acceptance-test-untestable">Acceptance test, untestable</a></li>
<li><a href="#velocityteamwork" id="toc-velocityteamwork">Velocity/teamwork</a></li>
</ul></li>
</ul>
</nav>
<h1 id="iteration-3-worksheet">Iteration 3 Worksheet</h1>
<h2 id="what-technical-debt-has-been-cleaned-up">What technical debt has
been cleaned up</h2>
<p>Show links to a commit where you paid off technical debt. Write 2-5
sentences that explain what debt was paid, and what its classification
is.</p>
<p>A piece of technical debt that has been paid off is moving the copy datebase code into its own class so that only one line of code needs to moved rather than
having to copy two entire methods from one class to another if changing the startup activity. Here is a link to before cleaning it up: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/15d8504961e3880345a09a0c7dc0831c745df82a/app/src/main/java/comp3350/highschoolhub/presentation/Connections.java#L53
This technical debt was reckless and inadvertent as we blindly copied the the copy database code from the sample project to our own project without thinking about
the structure of our project and SOLID design principles. Here is a link to after cleaning it up: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/91f3db90d195a40ff0494c137671b9f3a4db6b5a/app/src/main/java/comp3350/highschoolhub/presentation/Connections.java#L46 as well as a link to the new CopyDatabase class
which eliminates the need to copy entire methods from one class to another if the startup activity gets changed: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/91f3db90d195a40ff0494c137671b9f3a4db6b5a/app/src/main/java/comp3350/highschoolhub/business/CopyDatabase.java#L15.</p>
<h2 id="what-technical-debt-did-you-leave">What technical debt did you
leave?</h2>
<p>What one item would you like to fix, and can’t? Anything you write
will not be marked negatively. Classify this debt.</p>
<p>One item that we would like to fix is to get rid of the static variables from the AccessUsers class: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/blob/91f3db90d195a40ff0494c137671b9f3a4db6b5a/app/src/main/java/comp3350/highschoolhub/business/AccessUsers.java#L21-25 These variables make some of our classes tightly coupled
which is not good in case if we ever want to replace AccessUsers with another class in the future. This technical debt is classified as reckless and deliberate as we know that this
is not the best way to keep track of the logged user, profile user, etc. however we still made it like that as it was the easiest thing to do at the time and we have not had
the time to figure out a better way that makes our code more loosely coupled from this class.</p>
<h2 id="discuss-a-feature-or-user-story-that-was-cutre-prioritized">Discuss
a Feature or User Story that was cut/re-prioritized</h2>
<p>When did you change the priority of a Feature or User Story? Why was
it re-prioritized? Provide a link to the Feature or User Story. This can
be from any iteration.</p>
<p>A feature that we re-prioritized is the ability for a user to add multiple photos to their profile, which can be found here: https://code.cs.umanitoba.ca/3350-summer2023/highschool-hub/-/issues/22. During a team meeting at the beginning of iteration 3, we noticed we had too many features left to implement, and it was unlikely that we would be able to complete them all before the iteration 3 deadline. To focus on the higher priority features that were more essential to the app, we decided to cut some of our planned lower priority features for iteration 3, which included this one. Being able to add one photo to a profile is sufficient for the time being, and adding multiple photos is not an important functionality we require for the app to reflect our original vision. Therefore, this feature is now in the Future milestone, to be included in a theoretical future iteration.</p>
<h2 id="acceptance-testend-to-end">Acceptance test/end-to-end</h2>
<p>One of the tests that was coded, is testing that the user info on the user profile is displayed correctly. Firstly, the database had to be initialized, and since we were dealing only with user info, other access to the database was not required. 2 users were picked for which the test was to be carried out ( User 2 and 4), and before carrying out the test, all of the picked user's data was changed, to know what data is expected to be displayed. Secondly, using espresso to carry out the test, the test first logs into the user account using the userID and password, and right after that the app immediately takes us to the user's profile for which most of the data is displayed (Bio, Name, Marital status), all these info were checked that they exactly match the info we expected for the user, and all the tests were passed. Lastly, the list of high schools the users were enrolled in was tested by making sure the "High School Central" text was visible in the list of high schools adapter view for both users since this is the only high school they were enrolled in. We ensured the test was not flaky using 2 different approaches, the first was carrying out the test on 2 different user profiles, and the second is by changing the user's data to know if a test fails, where the root of the problem would be. Further approaches were taken as well, the sequence of the data checked was changed for both users, and the test was carried out for more than one different combination for the sequence.</p>

<h2 id="acceptance-test-untestable">Acceptance test, untestable</h2>
<p>What challenges did you face when creating acceptance tests? What was
difficult or impossible to test?</p>
<h2 id="velocityteamwork">Velocity/teamwork</h2>
<p>Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.</p>
<p>Here, we will compare estimated time vs. actual time for Iteration 1 and 2. The estimated times for each feature in a given Iteration are added to get the total estimated time for that Iteration. The actual time spent on an Iteration is taken from time tracking widget in the Milestones tab. Total time estimated for Iteration 1 was 7 days, and the actual time was 2 weeks and 3 hours. For Iteration 2, estimated time was 5 days and the actual time was 1 week, 7 hours and 30 minutes. Clearly our estimates got much better from Iteration 1 to Iteration 2. However, there is still room for improvement.</p>
</body>
</html>

