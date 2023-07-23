<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang xml:lang>
<head>
  <meta charset="utf-8" />
  <meta name="generator" content="pandoc" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
  <title>i3_worksheet</title>
  <style>
html {
color: #1a1a1a;
background-color: #fdfdfd;
}
body {
margin: 0 auto;
max-width: 36em;
padding-left: 50px;
padding-right: 50px;
padding-top: 50px;
padding-bottom: 50px;
hyphens: auto;
overflow-wrap: break-word;
text-rendering: optimizeLegibility;
font-kerning: normal;
}
@media (max-width: 600px) {
body {
font-size: 0.9em;
padding: 12px;
}
h1 {
font-size: 1.8em;
}
}
@media print {
html {
background-color: white;
}
body {
background-color: transparent;
color: black;
font-size: 12pt;
}
p, h2, h3 {
orphans: 3;
widows: 3;
}
h2, h3, h4 {
page-break-after: avoid;
}
}
p {
margin: 1em 0;
}
a {
color: #1a1a1a;
}
a:visited {
color: #1a1a1a;
}
img {
max-width: 100%;
}
h1, h2, h3, h4, h5, h6 {
margin-top: 1.4em;
}
h5, h6 {
font-size: 1em;
font-style: italic;
}
h6 {
font-weight: normal;
}
ol, ul {
padding-left: 1.7em;
margin-top: 1em;
}
li > ol, li > ul {
margin-top: 0;
}
blockquote {
margin: 1em 0 1em 1.7em;
padding-left: 1em;
border-left: 2px solid #e6e6e6;
color: #606060;
}
code {
font-family: Menlo, Monaco, Consolas, 'Lucida Console', monospace;
font-size: 85%;
margin: 0;
hyphens: manual;
}
pre {
margin: 1em 0;
overflow: auto;
}
pre code {
padding: 0;
overflow: visible;
overflow-wrap: normal;
}
.sourceCode {
background-color: transparent;
overflow: visible;
}
hr {
background-color: #1a1a1a;
border: none;
height: 1px;
margin: 1em 0;
}
table {
margin: 1em 0;
border-collapse: collapse;
width: 100%;
overflow-x: auto;
display: block;
font-variant-numeric: lining-nums tabular-nums;
}
table caption {
margin-bottom: 0.75em;
}
tbody {
margin-top: 0.5em;
border-top: 1px solid #1a1a1a;
border-bottom: 1px solid #1a1a1a;
}
th {
border-top: 1px solid #1a1a1a;
padding: 0.25em 0.5em 0.25em 0.5em;
}
td {
padding: 0.125em 0.5em 0.25em 0.5em;
}
header {
margin-bottom: 4em;
text-align: center;
}
#TOC li {
list-style: none;
}
#TOC ul {
padding-left: 1.3em;
}
#TOC > ul {
padding-left: 0;
}
#TOC a:not(:hover) {
text-decoration: none;
}
code{white-space: pre-wrap;}
span.smallcaps{font-variant: small-caps;}
div.columns{display: flex; gap: min(4vw, 1.5em);}
div.column{flex: auto; overflow-x: auto;}
div.hanging-indent{margin-left: 1.5em; text-indent: -1.5em;}
ul.task-list{list-style: none;}
ul.task-list li input[type="checkbox"] {
width: 0.8em;
margin: 0 0.8em 0.2em -1.6em;
vertical-align: middle;
}
.display.math{display: block; text-align: center; margin: 0.5rem auto;}
</style>
  <style type="text/css">nav {
border: 1px dotted;
padding: 1em;
}
nav::before {
font-weight: bold;
content: "Table of contents"
}
body {
max-width: 50em;
}
</style>
  <style>body { max-width: 50em; } tr.odd { background-color: lightgray; }</style>
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
<h2 id="what-technical-debt-did-you-leave">What technical debt did you
leave?</h2>
<p>What one item would you like to fix, and can’t? Anything you write
will not be marked negatively. Classify this debt.</p>
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

