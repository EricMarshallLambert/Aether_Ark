class Header extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML =`

    <Style>
        /* Style the navigation bar */
        .navbar {
        width: 100%;
        background-color: #555;
        overflow: auto;
        }
        
        /* Navbar links */
        .navbar a {
        float: left;
        text-align: center;
        padding: 12px;
        color: white;
        text-decoration: none;
        font-size: 17px;
        }
        
        /* Navbar links on mouse-over */
        .navbar a:hover {
        background-color: #000;
        }
        
        /* Current/active navbar link */
        .active {
        background-color: rgb(121, 3, 240);
        }
        
        /* Add responsiveness - will automatically display the navbar vertically instead of horizontally on screens less than 500 pixels */
        @media screen and (max-width: 500px) {
        .navbar a {
            float: none;
            display: block;
        }
        }
    </Style>
    <header>
    <div class="navbar">
    <a class="active" href="/user/user-home.html">          <i class="fa fa-fw fa-home"></i> Home</a> 
    <a href="/celestial-bodies/celestial-bodies-home.html"> <i class="fa fa-fw fa-search"></i> Celestial Bodies</a> 
    <a href="/solar-systems/solar-systems-home.html">       <i class="fa fa-fw fa-envelope"></i> Solar Systems</a> 
    <a href="/user/user-settings.html">                     <i class="fa fa-fw fa-user"></i> Settings</a>
    <a href="/user/logout.html" id="logout">                <i class="fa fa-fw fa-user"></i> Logout</a>
  </div>
    </header>
    
 `;
    }
}
customElements.define('header-component', Header);