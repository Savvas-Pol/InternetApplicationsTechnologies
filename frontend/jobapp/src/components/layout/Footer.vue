<template>
  <footer class="site-footer">
    <a href="#top" class="smoothscroll scroll-top">
      <span class="icon-keyboard_arrow_up"></span>
    </a>

    <div class="container">
      <div class="row mb-5">
        <div class="col-6 col-md-3 mb-4 mb-md-0">
          <h3>Search Trending</h3>
          <ul class="list-unstyled">
            <li>
              <router-link class="nav-link active" to="/about"
                >About</router-link
              >
            </li>
            <li>
              <router-link class="nav-link active" to="/admin"
                >Admin</router-link
              >
            </li>
            <li>
              <router-link class="nav-link active" to="/profile"
                >Profile</router-link
              >
            </li>
            <li>
              <router-link class="nav-link active" to="/settings"
                >Settings</router-link
              >
            </li>
          </ul>
        </div>
        <div class="col-6 col-md-3 mb-4 mb-md-0">
          <h3>Lists</h3>
          <ul class="list-unstyled">
            <li>
              <router-link class="nav-link active" to="/articles"
                >Articles</router-link
              >
            </li>
            <li>
              <router-link class="nav-link active" to="/ads">Ads</router-link>
            </li>
            <li>
              <router-link class="nav-link active" to="/posts"
                >Posts</router-link
              >
            </li>
          </ul>
        </div>
        <div class="col-6 col-md-3 mb-4 mb-md-0">
          <h3>Support</h3>
          <ul class="list-unstyled">
            <li><a href="#">Support</a></li>
            <li><a href="#">Privacy</a></li>
            <li><a href="#">Terms of Service</a></li>
          </ul>
        </div>
        <div class="col-6 col-md-3 mb-4 mb-md-0">
          <h3>Contact Us</h3>
          <div class="footer-social">
            <a href="#"><span class="icon-facebook"></span></a>
            <a href="#"><span class="icon-twitter"></span></a>
            <a href="#"><span class="icon-instagram"></span></a>
            <a href="#"><span class="icon-linkedin"></span></a>
          </div>
        </div>
      </div>

      <div class="row text-center">
        <div class="col-12">
          <p class="copyright">
            <small>
              Copyright &copy; @2021 All rights reserved | This template is made
              with
              <i class="icon-heart text-danger" aria-hidden="true"></i> by
              <a href="https://colorlib.com" target="_blank">Colorlib</a>
            </small>
          </p>
        </div>
      </div>
    </div>
  </footer>
</template>