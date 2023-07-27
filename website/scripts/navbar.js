// Navbar to import into all pages
let pagesPrepend = document.title == "Highschool Hub" ? "pages/" : "";
let assetsPrepend = document.title == "Highschool Hub" ? "" : "../";
let navbar = `
<nav id="navbar" class="navbar navbar-expand-lg sticky-top">
  <div class="container">
    <a class="navbar-brand text-primary me-4" href="../index.html">
      <div class="d-flex align-items-center">
        <img class="small-icon" src="${assetsPrepend}assets/svgs/school-solid.svg" />&nbsp;&nbsp;
        <span>Highschool Hub</span>
      </div>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navbarSupportedContent" class="collapse navbar-collapse ms-4">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="${pagesPrepend}vision.html">Vision</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pagesPrepend}features.html">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pagesPrepend}postmortem.html">Postmortem</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pagesPrepend}team.html">Team</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
`;

document.body.insertAdjacentHTML("afterbegin", navbar);
