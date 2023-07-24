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
<li><a href="#n1-acceptance-test" id="toc-n1-acceptance-test">n+1
Acceptance test</a></li>
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
<h2 id="acceptance-testend-to-end">Acceptance test/end-to-end</h2>
<p>Write a discussion about an end-to-end test that you wrote. What did
you test, how did you set up the test so it was not flaky? Provide a
link to that test.</p>
<h2 id="acceptance-test-untestable">Acceptance test, untestable</h2>
<p>What challenges did you face when creating acceptance tests? What was
difficult or impossible to test?</p>
<h2 id="n1-acceptance-test">n+1 Acceptance test</h2>
<p>From class, every team must post an acceptance test script to the
forum. Look at team n+1’s acceptance test, and record yourself
performing it (the android emulator can do screen recordings!). Post
that recording to the forum, link to it here.</p>
<h2 id="velocityteamwork">Velocity/teamwork</h2>
<p>Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.</p>
</body>
</html>

