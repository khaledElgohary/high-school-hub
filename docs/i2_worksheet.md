<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang xml:lang>
<head>
  <meta charset="utf-8" />
  <meta name="generator" content="pandoc" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes" />
  <title>i2_worksheet</title>
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
  <style>body { max-width: 50em; color: black; } tr.odd { background-color: lightgray; }</style>
  <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
  <![endif]-->
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
<h2 id="solid">SOLID</h2>
<p>Find a SOLID violation in the project of group <code>n+1</code>
(group 13 does group 1). Open an issue in their project with the
violation, clearly explaining the SOLID violation - specifying the type,
provide a link to that issue. Be sure your links in the issues are to
<strong>specific commits</strong> (not to <code>main</code>, or
<code>develop</code> as those will be changed).</p>
<p>Provide a link to the issue you created here.</p>
<h2 id="retrospective">Retrospective</h2>
<p>Describe how the retrospective has changed the way you are doing your
project. Is there evidence of the change in estimating/committing/peer
review/timelines/testing? Provide those links and evidence here - or
explain why there is not evidence.</p>
<h2 id="design-patterns">Design patterns</h2>
<p>Show links to your project where you use a well-known design pattern.
Which pattern is it? Provide links to the design pattern that you
used.</p>
<p><strong>Note</strong>: Though Dependency Injection is a programming
pattern, we would like to see a programming pattern other than
Dependency Injection.</p>
<h2 id="iteration-1-feedback-fixes">Iteration 1 Feedback fixes</h2>
<p>Provide a link to an issue opened by the grader.</p>
<p>Explain what the issue was, and why it was flagged. Explain what you
did to refactor or fix your code to address the issue. Provide links to
the commits where you fixed the issue.</p>
<p>No issues? Show 1 more piece of technical debt being paid off. Or
demonstrate how you are not adding debt by being prudent in your new
additions.</p>
</body>
</html>
