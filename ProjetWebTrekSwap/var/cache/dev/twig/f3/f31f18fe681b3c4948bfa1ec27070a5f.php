<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\CoreExtension;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;
use Twig\TemplateWrapper;

/* base-back.html.twig */
class __TwigTemplate_5f4b5336d6f14ee60a84b3e0bd68d8b6 extends Template
{
    private Source $source;
    /**
     * @var array<string, Template>
     */
    private array $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->parent = false;

        $this->blocks = [
            'title' => [$this, 'block_title'],
            'css' => [$this, 'block_css'],
            'navbar' => [$this, 'block_navbar'],
            'sidebar' => [$this, 'block_sidebar'],
            'body' => [$this, 'block_body'],
            'js' => [$this, 'block_js'],
        ];
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "base-back.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "base-back.html.twig"));

        // line 1
        yield "<!DOCTYPE html>
<html lang=\"en\">

<head>
  <meta charset=\"utf-8\">
  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">
  
  <title>";
        // line 8
        yield from $this->unwrap()->yieldBlock('title', $context, $blocks);
        yield "</title>
  
  <meta content=\"\" name=\"description\">
  <meta content=\"\" name=\"keywords\">

  <!-- Favicons -->
  <link href=\"";
        // line 14
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/TWlogo.png"), "html", null, true);
        yield "\" rel=\"icon\">
  <link href=\"";
        // line 15
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/TWlogo.png"), "html", null, true);
        yield "\" rel=\"apple-touch-icon\">

  <!-- Google Fonts -->
  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">
  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">
  ";
        // line 20
        yield from $this->unwrap()->yieldBlock('css', $context, $blocks);
        // line 33
        yield "  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
";
        // line 43
        yield from $this->unwrap()->yieldBlock('navbar', $context, $blocks);
        // line 272
        yield from $this->unwrap()->yieldBlock('sidebar', $context, $blocks);
        // line 393
        yield "  ";
        yield from $this->unwrap()->yieldBlock('body', $context, $blocks);
        // line 1040
        yield "  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id=\"footer\" class=\"footer\">
    <div class=\"copyright\">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class=\"credits\">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href=\"https://bootstrapmade.com/\">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>
";
        // line 1057
        yield from $this->unwrap()->yieldBlock('js', $context, $blocks);
        // line 1071
        yield "</body>

</html>";
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        yield from [];
    }

    // line 8
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_title(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "title"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "title"));

        yield "Dashboard - NiceAdmin Bootstrap Template";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 20
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_css(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "css"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "css"));

        // line 21
        yield "  <!-- Vendor CSS Files -->
  <link href=\"";
        // line 22
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/bootstrap/css/bootstrap.min.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  <link href=\"";
        // line 23
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/bootstrap-icons/bootstrap-icons.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  <link href=\"";
        // line 24
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/boxicons/css/boxicons.min.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  <link href=\"";
        // line 25
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/quill/quill.snow.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  <link href=\"";
        // line 26
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/quill/quill.bubble.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  <link href=\"";
        // line 27
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/remixicon/remixicon.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  <link href=\"";
        // line 28
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/simple-datatables/style.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">

  <!-- Template Main CSS File -->
  <link href=\"";
        // line 31
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/css/style.css"), "html", null, true);
        yield "\" rel=\"stylesheet\">
  ";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 43
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_navbar(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "navbar"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "navbar"));

        // line 44
        yield "  <!-- ======= Header ======= -->
  <header id=\"header\" class=\"header fixed-top d-flex align-items-center\">

    <div class=\"d-flex align-items-center justify-content-between\">
      <a href=\"index.html\" class=\"logo d-flex align-items-center\">
        <img src=\"";
        // line 49
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/TWlogo.png"), "html", null, true);
        yield "\" alt=\"\">
        <span class=\"d-none d-lg-block\">TrekSwap</span>
      </a>
      <i class=\"bi bi-list toggle-sidebar-btn\"></i>
    </div><!-- End Logo -->

   

    <nav class=\"header-nav ms-auto\">
      <ul class=\"d-flex align-items-center\">

        <li class=\"nav-item d-block d-lg-none\">
          <a class=\"nav-link nav-icon search-bar-toggle \" href=\"#\">
            <i class=\"bi bi-search\"></i>
          </a>
        </li><!-- End Search Icon-->

        <li class=\"nav-item dropdown\">

          <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">
            <i class=\"bi bi-bell\"></i>
            <span class=\"badge bg-primary badge-number\">4</span>
          </a><!-- End Notification Icon -->

          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications\">
            <li class=\"dropdown-header\">
              You have 4 new notifications
              <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">View all</span></a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-exclamation-circle text-warning\"></i>
              <div>
                <h4>Lorem Ipsum</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>30 min. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-x-circle text-danger\"></i>
              <div>
                <h4>Atque rerum nesciunt</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>1 hr. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-check-circle text-success\"></i>
              <div>
                <h4>Sit rerum fuga</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>2 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-info-circle text-primary\"></i>
              <div>
                <h4>Dicta reprehenderit</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>4 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>
            <li class=\"dropdown-footer\">
              <a href=\"#\">Show all notifications</a>
            </li>

          </ul><!-- End Notification Dropdown Items -->

        </li><!-- End Notification Nav -->

        <li class=\"nav-item dropdown\">

          <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">
            <i class=\"bi bi-chat-left-text\"></i>
            <span class=\"badge bg-success badge-number\">3</span>
          </a><!-- End Messages Icon -->

          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow messages\">
            <li class=\"dropdown-header\">
              You have 3 new messages
              <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">View all</span></a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"message-item\">
              <a href=\"#\">
                <img src=\"";
        // line 159
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/messages-1.jpg"), "html", null, true);
        yield "\" alt=\"\" class=\"rounded-circle\">
                <div>
                  <h4>Maria Hudson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>4 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"message-item\">
              <a href=\"#\">
                <img src=\"";
        // line 173
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/messages-2.jpg"), "html", null, true);
        yield "\" alt=\"\" class=\"rounded-circle\">
                <div>
                  <h4>Anna Nelson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>6 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"message-item\">
              <a href=\"#\">
                <img src=\"";
        // line 187
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/messages-3.jpg"), "html", null, true);
        yield "\" alt=\"\" class=\"rounded-circle\">
                <div>
                  <h4>David Muldon</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>8 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"dropdown-footer\">
              <a href=\"#\">Show all messages</a>
            </li>

          </ul><!-- End Messages Dropdown Items -->

        </li><!-- End Messages Nav -->

        <li class=\"nav-item dropdown pe-3\">

          <a class=\"nav-link nav-profile d-flex align-items-center pe-0\" href=\"#\" data-bs-toggle=\"dropdown\">
            ";
        // line 210
        if (CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 210, $this->source); })()), "user", [], "any", false, false, false, 210), "photoProfile", [], "any", false, false, false, 210)) {
            // line 211
            yield "              <img src=\"";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/profile_pictures/" . CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 211, $this->source); })()), "user", [], "any", false, false, false, 211), "photoProfile", [], "any", false, false, false, 211))), "html", null, true);
            yield "\" alt=\"Profile\" class=\"rounded-circle\">
            ";
        } else {
            // line 213
            yield "              <img src=\"";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/profile-img.jpg"), "html", null, true);
            yield "\" alt=\"Profile\" class=\"rounded-circle\">
            ";
        }
        // line 215
        yield "            <span class=\"d-none d-md-block dropdown-toggle ps-2\">";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 215, $this->source); })()), "user", [], "any", false, false, false, 215), "prenom", [], "any", false, false, false, 215), "html", null, true);
        yield " ";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 215, $this->source); })()), "user", [], "any", false, false, false, 215), "nom", [], "any", false, false, false, 215), "html", null, true);
        yield "</span>
          </a><!-- End Profile Image Icon -->

          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow profile\">
            <li class=\"dropdown-header\">
              <h6>";
        // line 220
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 220, $this->source); })()), "user", [], "any", false, false, false, 220), "prenom", [], "any", false, false, false, 220), "html", null, true);
        yield " ";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 220, $this->source); })()), "user", [], "any", false, false, false, 220), "nom", [], "any", false, false, false, 220), "html", null, true);
        yield "</h6>
              <span>";
        // line 221
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 221, $this->source); })()), "user", [], "any", false, false, false, 221), "typeUser", [], "any", false, false, false, 221), "html", null, true);
        yield "</span>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"";
        // line 228
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_profile");
        yield "\">
                <i class=\"bi bi-person\"></i>
                <span>My Profile</span>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"users-profile.html\">
                <i class=\"bi bi-gear\"></i>
                <span>Account Settings</span>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"pages-faq.html\">
                <i class=\"bi bi-question-circle\"></i>
                <span>Need Help?</span>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"";
        // line 258
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_logout");
        yield "\">
                <i class=\"bi bi-box-arrow-right\"></i>
                <span>Sign Out</span>
              </a>
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->

      </ul>
    </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->
";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 272
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_sidebar(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "sidebar"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "sidebar"));

        // line 273
        yield "  <!-- ======= Sidebar ======= -->
  <aside id=\"sidebar\" class=\"sidebar\">

    <ul class=\"sidebar-nav\" id=\"sidebar-nav\">

      <li class=\"nav-item\">
        <a class=\"nav-link \" href=\"";
        // line 279
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_Abonnements");
        yield "\">
          <i class=\"bi bi-grid\"></i>
          <span>Dashboard</span>
        </a>
      </li>

      ";
        // line 285
        if ((CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 285, $this->source); })()), "user", [], "any", false, false, false, 285) && (CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 285, $this->source); })()), "user", [], "any", false, false, false, 285), "typeUser", [], "any", false, false, false, 285) == "Admin"))) {
            // line 286
            yield "      <li class=\"nav-item\">
        <a class=\"nav-link\" href=\"";
            // line 287
            yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_admin");
            yield "\">
          <i class=\"bi bi-shield-lock\"></i>
          <span>Admin Dashboard</span>
        </a>
      </li>
      <li class=\"nav-item\">
        <a class=\"nav-link\" href=\"";
            // line 293
            yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_Abonnements");
            yield "\">
          <i class=\"bi bi-gear\"></i>
          <span>Back Office</span>
        </a>
      </li>
      ";
        }
        // line 299
        yield "
      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#components-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-menu-button-wide\"></i><span>Pack Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"components-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            
             <a href=\"/packshow\">
              <i class=\"bi bi-circle\"></i><span> Packs </span>
            </a>
          </li>
          <li>
            <a href=\"/Abonshow\">
              <i class=\"bi bi-circle\"></i><span>Abonnements</span>
            </a>
          </li>
        </ul>
      </li><!-- End Components Nav -->

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#forms-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-journal-text\"></i><span>Destinations modules</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"forms-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"/DestinationBackShow\">
              <i class=\"bi bi-circle\"></i><span>Destinations</span>
            </a>
          </li>
          <li>
            <a href=\"/activite/show\">
              <i class=\"bi bi-circle\"></i><span>Activites</span>
            </a>
          </li>
        </ul>
      </li><!-- End Forms Nav -->

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#tables-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-layout-text-window-reverse\"></i><span>Partenaire Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"tables-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"/Partenaires\">
              <i class=\"bi bi-circle\"></i><span>Partenaire</span>
            </a>
          </li>
          <li>
            <a href=\"/Catshow\">
              <i class=\"bi bi-circle\"></i><span>Categorie</span>
            </a>
          </li>
        </ul>
      </li><!-- End Tables Nav -->

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#charts-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-bar-chart\"></i><span>mission Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"charts-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"charts-chartjs.html\">
              <i class=\"bi bi-circle\"></i><span>mission</span>
            </a>
          </li>
          <li>
            <a href=\"charts-apexcharts.html\">
              <i class=\"bi bi-circle\"></i><span>recompense</span>
            </a>
          </li>
        </ul>
      </li><!-- End Charts Nav -->
      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#charts-navvvv\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-bar-chart\"></i><span>Reclamtion Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"charts-navvvv\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"/reclamation/back\">
              <i class=\"bi bi-circle\"></i><span>Reclamation</span>
            </a>
          </li>
          <li>
            <a href=\"charts-apexcharts.html\">
              <i class=\"bi bi-circle\"></i><span>Reponse</span>
            </a>
          </li>
        </ul>
      </li>
      </li><!-- End Profile Page Nav -->

  </aside><!-- End Sidebar-->
  ";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 393
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_body(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "body"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "body"));

        // line 394
        yield "  <main id=\"main\" class=\"main\">

    <div class=\"pagetitle\">
      <h1>Dashboard</h1>
      <nav>
        <ol class=\"breadcrumb\">
          <li class=\"breadcrumb-item\"><a href=\"index.html\">Home</a></li>
          <li class=\"breadcrumb-item active\">Dashboard</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class=\"section dashboard\">
      <div class=\"row\">

        <!-- Left side columns -->
        <div class=\"col-lg-8\">
          <div class=\"row\">

            <!-- Sales Card -->
            <div class=\"col-xxl-4 col-md-6\">
              <div class=\"card info-card sales-card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Sales <span>| Today</span></h5>

                  <div class=\"d-flex align-items-center\">
                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">
                      <i class=\"bi bi-cart\"></i>
                    </div>
                    <div class=\"ps-3\">
                      <h6>145</h6>
                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>

                    </div>
                  </div>
                </div>

              </div>
            </div><!-- End Sales Card -->

            <!-- Revenue Card -->
            <div class=\"col-xxl-4 col-md-6\">
              <div class=\"card info-card revenue-card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Revenue <span>| This Month</span></h5>

                  <div class=\"d-flex align-items-center\">
                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">
                      <i class=\"bi bi-currency-dollar\"></i>
                    </div>
                    <div class=\"ps-3\">
                      <h6>\$3,264</h6>
                      <span class=\"text-success small pt-1 fw-bold\">8%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>

                    </div>
                  </div>
                </div>

              </div>
            </div><!-- End Revenue Card -->

            <!-- Customers Card -->
            <div class=\"col-xxl-4 col-xl-12\">

              <div class=\"card info-card customers-card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Customers <span>| This Year</span></h5>

                  <div class=\"d-flex align-items-center\">
                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">
                      <i class=\"bi bi-people\"></i>
                    </div>
                    <div class=\"ps-3\">
                      <h6>1244</h6>
                      <span class=\"text-danger small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">decrease</span>

                    </div>
                  </div>

                </div>
              </div>

            </div><!-- End Customers Card -->

            <!-- Reports -->
            <div class=\"col-12\">
              <div class=\"card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Reports <span>/Today</span></h5>

                  <!-- Line Chart -->
                  <div id=\"reportsChart\"></div>

                  <script>
                    document.addEventListener(\"DOMContentLoaded\", () => {
                      new ApexCharts(document.querySelector(\"#reportsChart\"), {
                        series: [{
                          name: 'Sales',
                          data: [31, 40, 28, 51, 42, 82, 56],
                        }, {
                          name: 'Revenue',
                          data: [11, 32, 45, 32, 34, 52, 41]
                        }, {
                          name: 'Customers',
                          data: [15, 11, 32, 18, 9, 24, 11]
                        }],
                        chart: {
                          height: 350,
                          type: 'area',
                          toolbar: {
                            show: false
                          },
                        },
                        markers: {
                          size: 4
                        },
                        colors: ['#4154f1', '#2eca6a', '#ff771d'],
                        fill: {
                          type: \"gradient\",
                          gradient: {
                            shadeIntensity: 1,
                            opacityFrom: 0.3,
                            opacityTo: 0.4,
                            stops: [0, 90, 100]
                          }
                        },
                        dataLabels: {
                          enabled: false
                        },
                        stroke: {
                          curve: 'smooth',
                          width: 2
                        },
                        xaxis: {
                          type: 'datetime',
                          categories: [\"2018-09-19T00:00:00.000Z\", \"2018-09-19T01:30:00.000Z\", \"2018-09-19T02:30:00.000Z\", \"2018-09-19T03:30:00.000Z\", \"2018-09-19T04:30:00.000Z\", \"2018-09-19T05:30:00.000Z\", \"2018-09-19T06:30:00.000Z\"]
                        },
                        tooltip: {
                          x: {
                            format: 'dd/MM/yy HH:mm'
                          },
                        }
                      }).render();
                    });
                  </script>
                  <!-- End Line Chart -->

                </div>

              </div>
            </div><!-- End Reports -->

            <!-- Recent Sales -->
            <div class=\"col-12\">
              <div class=\"card recent-sales overflow-auto\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Recent Sales <span>| Today</span></h5>

                  <table class=\"table table-borderless datatable\">
                    <thead>
                      <tr>
                        <th scope=\"col\">#</th>
                        <th scope=\"col\">Customer</th>
                        <th scope=\"col\">Product</th>
                        <th scope=\"col\">Price</th>
                        <th scope=\"col\">Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2457</a></th>
                        <td>Brandon Jacob</td>
                        <td><a href=\"#\" class=\"text-primary\">At praesentium minu</a></td>
                        <td>\$64</td>
                        <td><span class=\"badge bg-success\">Approved</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2147</a></th>
                        <td>Bridie Kessler</td>
                        <td><a href=\"#\" class=\"text-primary\">Blanditiis dolor omnis similique</a></td>
                        <td>\$47</td>
                        <td><span class=\"badge bg-warning\">Pending</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2049</a></th>
                        <td>Ashleigh Langosh</td>
                        <td><a href=\"#\" class=\"text-primary\">At recusandae consectetur</a></td>
                        <td>\$147</td>
                        <td><span class=\"badge bg-success\">Approved</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2644</a></th>
                        <td>Angus Grady</td>
                        <td><a href=\"#\" class=\"text-primar\">Ut voluptatem id earum et</a></td>
                        <td>\$67</td>
                        <td><span class=\"badge bg-danger\">Rejected</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2644</a></th>
                        <td>Raheem Lehner</td>
                        <td><a href=\"#\" class=\"text-primary\">Sunt similique distinctio</a></td>
                        <td>\$165</td>
                        <td><span class=\"badge bg-success\">Approved</span></td>
                      </tr>
                    </tbody>
                  </table>

                </div>

              </div>
            </div><!-- End Recent Sales -->

            <!-- Top Selling -->
            <div class=\"col-12\">
              <div class=\"card top-selling overflow-auto\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body pb-0\">
                  <h5 class=\"card-title\">Top Selling <span>| Today</span></h5>

                  <table class=\"table table-borderless\">
                    <thead>
                      <tr>
                        <th scope=\"col\">Preview</th>
                        <th scope=\"col\">Product</th>
                        <th scope=\"col\">Price</th>
                        <th scope=\"col\">Sold</th>
                        <th scope=\"col\">Revenue</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"";
        // line 708
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/product-1.jpg"), "html", null, true);
        yield "\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Ut inventore ipsa voluptas nulla</a></td>
                        <td>\$64</td>
                        <td class=\"fw-bold\">124</td>
                        <td>\$5,828</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"";
        // line 715
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/product-2.jpg"), "html", null, true);
        yield "\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Exercitationem similique doloremque</a></td>
                        <td>\$46</td>
                        <td class=\"fw-bold\">98</td>
                        <td>\$4,508</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"";
        // line 722
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/product-3.jpg"), "html", null, true);
        yield "\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Doloribus nisi exercitationem</a></td>
                        <td>\$59</td>
                        <td class=\"fw-bold\">74</td>
                        <td>\$4,366</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"";
        // line 729
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/product-4.jpg"), "html", null, true);
        yield "\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Officiis quaerat sint rerum error</a></td>
                        <td>\$32</td>
                        <td class=\"fw-bold\">63</td>
                        <td>\$2,016</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"";
        // line 736
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/product-5.jpg"), "html", null, true);
        yield "\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Sit unde debitis delectus repellendus</a></td>
                        <td>\$79</td>
                        <td class=\"fw-bold\">41</td>
                        <td>\$3,239</td>
                      </tr>
                    </tbody>
                  </table>

                </div>

              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class=\"col-lg-4\">

          <!-- Recent Activity -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body\">
              <h5 class=\"card-title\">Recent Activity <span>| Today</span></h5>

              <div class=\"activity\">

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">32 min</div>
                  <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                  <div class=\"activity-content\">
                    Quia quae rerum <a href=\"#\" class=\"fw-bold text-dark\">explicabo officiis</a> beatae
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">56 min</div>
                  <i class='bi bi-circle-fill activity-badge text-danger align-self-start'></i>
                  <div class=\"activity-content\">
                    Voluptatem blanditiis blanditiis eveniet
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">2 hrs</div>
                  <i class='bi bi-circle-fill activity-badge text-primary align-self-start'></i>
                  <div class=\"activity-content\">
                    Voluptates corrupti molestias voluptatem
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">1 day</div>
                  <i class='bi bi-circle-fill activity-badge text-info align-self-start'></i>
                  <div class=\"activity-content\">
                    Tempore autem saepe <a href=\"#\" class=\"fw-bold text-dark\">occaecati voluptatem</a> tempore
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">2 days</div>
                  <i class='bi bi-circle-fill activity-badge text-warning align-self-start'></i>
                  <div class=\"activity-content\">
                    Est sit eum reiciendis exercitationem
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">4 weeks</div>
                  <i class='bi bi-circle-fill activity-badge text-muted align-self-start'></i>
                  <div class=\"activity-content\">
                    Dicta dolorem harum nulla eius. Ut quidem quidem sit quas
                  </div>
                </div><!-- End activity item-->

              </div>

            </div>
          </div><!-- End Recent Activity -->

          <!-- Budget Report -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body pb-0\">
              <h5 class=\"card-title\">Budget Report <span>| This Month</span></h5>

              <div id=\"budgetChart\" style=\"min-height: 400px;\" class=\"echart\"></div>

              <script>
                document.addEventListener(\"DOMContentLoaded\", () => {
                  var budgetChart = echarts.init(document.querySelector(\"#budgetChart\")).setOption({
                    legend: {
                      data: ['Allocated Budget', 'Actual Spending']
                    },
                    radar: {
                      // shape: 'circle',
                      indicator: [{
                          name: 'Sales',
                          max: 6500
                        },
                        {
                          name: 'Administration',
                          max: 16000
                        },
                        {
                          name: 'Information Technology',
                          max: 30000
                        },
                        {
                          name: 'Customer Support',
                          max: 38000
                        },
                        {
                          name: 'Development',
                          max: 52000
                        },
                        {
                          name: 'Marketing',
                          max: 25000
                        }
                      ]
                    },
                    series: [{
                      name: 'Budget vs spending',
                      type: 'radar',
                      data: [{
                          value: [4200, 3000, 20000, 35000, 50000, 18000],
                          name: 'Allocated Budget'
                        },
                        {
                          value: [5000, 14000, 28000, 26000, 42000, 21000],
                          name: 'Actual Spending'
                        }
                      ]
                    }]
                  });
                });
              </script>

            </div>
          </div><!-- End Budget Report -->

          <!-- Website Traffic -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body pb-0\">
              <h5 class=\"card-title\">Website Traffic <span>| Today</span></h5>

              <div id=\"trafficChart\" style=\"min-height: 400px;\" class=\"echart\"></div>

              <script>
                document.addEventListener(\"DOMContentLoaded\", () => {
                  echarts.init(document.querySelector(\"#trafficChart\")).setOption({
                    tooltip: {
                      trigger: 'item'
                    },
                    legend: {
                      top: '5%',
                      left: 'center'
                    },
                    series: [{
                      name: 'Access From',
                      type: 'pie',
                      radius: ['40%', '70%'],
                      avoidLabelOverlap: false,
                      label: {
                        show: false,
                        position: 'center'
                      },
                      emphasis: {
                        label: {
                          show: true,
                          fontSize: '18',
                          fontWeight: 'bold'
                        }
                      },
                      labelLine: {
                        show: false
                      },
                      data: [{
                          value: 1048,
                          name: 'Search Engine'
                        },
                        {
                          value: 735,
                          name: 'Direct'
                        },
                        {
                          value: 580,
                          name: 'Email'
                        },
                        {
                          value: 484,
                          name: 'Union Ads'
                        },
                        {
                          value: 300,
                          name: 'Video Ads'
                        }
                      ]
                    }]
                  });
                });
              </script>

            </div>
          </div><!-- End Website Traffic -->

          <!-- News & Updates Traffic -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body pb-0\">
              <h5 class=\"card-title\">News &amp; Updates <span>| Today</span></h5>

              <div class=\"news\">
                <div class=\"post-item clearfix\">
                  <img src=\"";
        // line 1001
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/news-1.jpg"), "html", null, true);
        yield "\" alt=\"\">
                  <h4><a href=\"#\">Nihil blanditiis at in nihil autem</a></h4>
                  <p>Sit recusandae non aspernatur laboriosam. Quia enim eligendi sed ut harum...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"";
        // line 1007
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/news-2.jpg"), "html", null, true);
        yield "\" alt=\"\">
                  <h4><a href=\"#\">Quidem autem et impedit</a></h4>
                  <p>Illo nemo neque maiores vitae officiis cum eum turos elan dries werona nande...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"";
        // line 1013
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/news-3.jpg"), "html", null, true);
        yield "\" alt=\"\">
                  <h4><a href=\"#\">Id quia et et ut maxime similique occaecati ut</a></h4>
                  <p>Fugiat voluptas vero eaque accusantium eos. Consequuntur sed ipsam et totam...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"";
        // line 1019
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/news-4.jpg"), "html", null, true);
        yield "\" alt=\"\">
                  <h4><a href=\"#\">Laborum corporis quo dara net para</a></h4>
                  <p>Qui enim quia optio. Eligendi aut asperiores enim repellendusvel rerum cuder...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"";
        // line 1025
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/news-5.jpg"), "html", null, true);
        yield "\" alt=\"\">
                  <h4><a href=\"#\">Et dolores corrupti quae illo quod dolor</a></h4>
                  <p>Odit ut eveniet modi reiciendis. Atque cupiditate libero beatae dignissimos eius...</p>
                </div>

              </div><!-- End sidebar recent posts-->

            </div>
          </div><!-- End News & Updates -->

        </div><!-- End Right side columns -->

      </div>
    </section>
";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 1057
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_js(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "js"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "js"));

        // line 1058
        yield "  <!-- Vendor JS Files -->
  <script src=\"";
        // line 1059
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/apexcharts/apexcharts.min.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1060
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/bootstrap/js/bootstrap.bundle.min.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1061
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/chart.js/chart.umd.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1062
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/echarts/echarts.min.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1063
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/quill/quill.min.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1064
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/simple-datatables/simple-datatables.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1065
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/tinymce/tinymce.min.js"), "html", null, true);
        yield "\"></script>
  <script src=\"";
        // line 1066
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/vendor/php-email-form/validate.js"), "html", null, true);
        yield "\"></script>

  <!-- Template Main JS File -->
  <script src=\"";
        // line 1069
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/js/main.js"), "html", null, true);
        yield "\"></script>
";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    /**
     * @codeCoverageIgnore
     */
    public function getTemplateName(): string
    {
        return "base-back.html.twig";
    }

    /**
     * @codeCoverageIgnore
     */
    public function isTraitable(): bool
    {
        return false;
    }

    /**
     * @codeCoverageIgnore
     */
    public function getDebugInfo(): array
    {
        return array (  1419 => 1069,  1413 => 1066,  1409 => 1065,  1405 => 1064,  1401 => 1063,  1397 => 1062,  1393 => 1061,  1389 => 1060,  1385 => 1059,  1382 => 1058,  1369 => 1057,  1343 => 1025,  1334 => 1019,  1325 => 1013,  1316 => 1007,  1307 => 1001,  1039 => 736,  1029 => 729,  1019 => 722,  1009 => 715,  999 => 708,  683 => 394,  670 => 393,  566 => 299,  557 => 293,  548 => 287,  545 => 286,  543 => 285,  534 => 279,  526 => 273,  513 => 272,  488 => 258,  455 => 228,  445 => 221,  439 => 220,  428 => 215,  422 => 213,  416 => 211,  414 => 210,  388 => 187,  371 => 173,  354 => 159,  241 => 49,  234 => 44,  221 => 43,  208 => 31,  202 => 28,  198 => 27,  194 => 26,  190 => 25,  186 => 24,  182 => 23,  178 => 22,  175 => 21,  162 => 20,  139 => 8,  126 => 1071,  124 => 1057,  105 => 1040,  102 => 393,  100 => 272,  98 => 43,  86 => 33,  84 => 20,  76 => 15,  72 => 14,  63 => 8,  54 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("<!DOCTYPE html>
<html lang=\"en\">

<head>
  <meta charset=\"utf-8\">
  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">
  
  <title>{% block title %}Dashboard - NiceAdmin Bootstrap Template{% endblock %}</title>
  
  <meta content=\"\" name=\"description\">
  <meta content=\"\" name=\"keywords\">

  <!-- Favicons -->
  <link href=\"{{asset('backTemp/img/TWlogo.png')}}\" rel=\"icon\">
  <link href=\"{{asset('backTemp/img/TWlogo.png')}}\" rel=\"apple-touch-icon\">

  <!-- Google Fonts -->
  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">
  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">
  {% block css %}
  <!-- Vendor CSS Files -->
  <link href=\"{{asset('backTemp/vendor/bootstrap/css/bootstrap.min.css')}}\" rel=\"stylesheet\">
  <link href=\"{{asset('backTemp/vendor/bootstrap-icons/bootstrap-icons.css')}}\" rel=\"stylesheet\">
  <link href=\"{{asset('backTemp/vendor/boxicons/css/boxicons.min.css')}}\" rel=\"stylesheet\">
  <link href=\"{{asset('backTemp/vendor/quill/quill.snow.css')}}\" rel=\"stylesheet\">
  <link href=\"{{asset('backTemp/vendor/quill/quill.bubble.css')}}\" rel=\"stylesheet\">
  <link href=\"{{asset('backTemp/vendor/remixicon/remixicon.css')}}\" rel=\"stylesheet\">
  <link href=\"{{asset('backTemp/vendor/simple-datatables/style.css')}}\" rel=\"stylesheet\">

  <!-- Template Main CSS File -->
  <link href=\"{{asset('backTemp/css/style.css')}}\" rel=\"stylesheet\">
  {% endblock %}
  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
{% block navbar %}
  <!-- ======= Header ======= -->
  <header id=\"header\" class=\"header fixed-top d-flex align-items-center\">

    <div class=\"d-flex align-items-center justify-content-between\">
      <a href=\"index.html\" class=\"logo d-flex align-items-center\">
        <img src=\"{{asset('backTemp/img/TWlogo.png')}}\" alt=\"\">
        <span class=\"d-none d-lg-block\">TrekSwap</span>
      </a>
      <i class=\"bi bi-list toggle-sidebar-btn\"></i>
    </div><!-- End Logo -->

   

    <nav class=\"header-nav ms-auto\">
      <ul class=\"d-flex align-items-center\">

        <li class=\"nav-item d-block d-lg-none\">
          <a class=\"nav-link nav-icon search-bar-toggle \" href=\"#\">
            <i class=\"bi bi-search\"></i>
          </a>
        </li><!-- End Search Icon-->

        <li class=\"nav-item dropdown\">

          <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">
            <i class=\"bi bi-bell\"></i>
            <span class=\"badge bg-primary badge-number\">4</span>
          </a><!-- End Notification Icon -->

          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications\">
            <li class=\"dropdown-header\">
              You have 4 new notifications
              <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">View all</span></a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-exclamation-circle text-warning\"></i>
              <div>
                <h4>Lorem Ipsum</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>30 min. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-x-circle text-danger\"></i>
              <div>
                <h4>Atque rerum nesciunt</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>1 hr. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-check-circle text-success\"></i>
              <div>
                <h4>Sit rerum fuga</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>2 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"notification-item\">
              <i class=\"bi bi-info-circle text-primary\"></i>
              <div>
                <h4>Dicta reprehenderit</h4>
                <p>Quae dolorem earum veritatis oditseno</p>
                <p>4 hrs. ago</p>
              </div>
            </li>

            <li>
              <hr class=\"dropdown-divider\">
            </li>
            <li class=\"dropdown-footer\">
              <a href=\"#\">Show all notifications</a>
            </li>

          </ul><!-- End Notification Dropdown Items -->

        </li><!-- End Notification Nav -->

        <li class=\"nav-item dropdown\">

          <a class=\"nav-link nav-icon\" href=\"#\" data-bs-toggle=\"dropdown\">
            <i class=\"bi bi-chat-left-text\"></i>
            <span class=\"badge bg-success badge-number\">3</span>
          </a><!-- End Messages Icon -->

          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow messages\">
            <li class=\"dropdown-header\">
              You have 3 new messages
              <a href=\"#\"><span class=\"badge rounded-pill bg-primary p-2 ms-2\">View all</span></a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"message-item\">
              <a href=\"#\">
                <img src=\"{{asset('backTemp/img/messages-1.jpg')}}\" alt=\"\" class=\"rounded-circle\">
                <div>
                  <h4>Maria Hudson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>4 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"message-item\">
              <a href=\"#\">
                <img src=\"{{asset('backTemp/img/messages-2.jpg')}}\" alt=\"\" class=\"rounded-circle\">
                <div>
                  <h4>Anna Nelson</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>6 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"message-item\">
              <a href=\"#\">
                <img src=\"{{asset('backTemp/img/messages-3.jpg')}}\" alt=\"\" class=\"rounded-circle\">
                <div>
                  <h4>David Muldon</h4>
                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                  <p>8 hrs. ago</p>
                </div>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li class=\"dropdown-footer\">
              <a href=\"#\">Show all messages</a>
            </li>

          </ul><!-- End Messages Dropdown Items -->

        </li><!-- End Messages Nav -->

        <li class=\"nav-item dropdown pe-3\">

          <a class=\"nav-link nav-profile d-flex align-items-center pe-0\" href=\"#\" data-bs-toggle=\"dropdown\">
            {% if app.user.photoProfile %}
              <img src=\"{{ asset('uploads/profile_pictures/' ~ app.user.photoProfile) }}\" alt=\"Profile\" class=\"rounded-circle\">
            {% else %}
              <img src=\"{{asset('backTemp/img/profile-img.jpg')}}\" alt=\"Profile\" class=\"rounded-circle\">
            {% endif %}
            <span class=\"d-none d-md-block dropdown-toggle ps-2\">{{ app.user.prenom }} {{ app.user.nom }}</span>
          </a><!-- End Profile Image Icon -->

          <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow profile\">
            <li class=\"dropdown-header\">
              <h6>{{ app.user.prenom }} {{ app.user.nom }}</h6>
              <span>{{ app.user.typeUser }}</span>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"{{ path('app_profile') }}\">
                <i class=\"bi bi-person\"></i>
                <span>My Profile</span>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"users-profile.html\">
                <i class=\"bi bi-gear\"></i>
                <span>Account Settings</span>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"pages-faq.html\">
                <i class=\"bi bi-question-circle\"></i>
                <span>Need Help?</span>
              </a>
            </li>
            <li>
              <hr class=\"dropdown-divider\">
            </li>

            <li>
              <a class=\"dropdown-item d-flex align-items-center\" href=\"{{ path('app_logout') }}\">
                <i class=\"bi bi-box-arrow-right\"></i>
                <span>Sign Out</span>
              </a>
            </li>

          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->

      </ul>
    </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->
{% endblock %}
{% block sidebar %}
  <!-- ======= Sidebar ======= -->
  <aside id=\"sidebar\" class=\"sidebar\">

    <ul class=\"sidebar-nav\" id=\"sidebar-nav\">

      <li class=\"nav-item\">
        <a class=\"nav-link \" href=\"{{ path('app_Abonnements') }}\">
          <i class=\"bi bi-grid\"></i>
          <span>Dashboard</span>
        </a>
      </li>

      {% if app.user and app.user.typeUser == 'Admin' %}
      <li class=\"nav-item\">
        <a class=\"nav-link\" href=\"{{ path('app_admin') }}\">
          <i class=\"bi bi-shield-lock\"></i>
          <span>Admin Dashboard</span>
        </a>
      </li>
      <li class=\"nav-item\">
        <a class=\"nav-link\" href=\"{{ path('app_Abonnements') }}\">
          <i class=\"bi bi-gear\"></i>
          <span>Back Office</span>
        </a>
      </li>
      {% endif %}

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#components-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-menu-button-wide\"></i><span>Pack Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"components-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            
             <a href=\"/packshow\">
              <i class=\"bi bi-circle\"></i><span> Packs </span>
            </a>
          </li>
          <li>
            <a href=\"/Abonshow\">
              <i class=\"bi bi-circle\"></i><span>Abonnements</span>
            </a>
          </li>
        </ul>
      </li><!-- End Components Nav -->

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#forms-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-journal-text\"></i><span>Destinations modules</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"forms-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"/DestinationBackShow\">
              <i class=\"bi bi-circle\"></i><span>Destinations</span>
            </a>
          </li>
          <li>
            <a href=\"/activite/show\">
              <i class=\"bi bi-circle\"></i><span>Activites</span>
            </a>
          </li>
        </ul>
      </li><!-- End Forms Nav -->

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#tables-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-layout-text-window-reverse\"></i><span>Partenaire Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"tables-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"/Partenaires\">
              <i class=\"bi bi-circle\"></i><span>Partenaire</span>
            </a>
          </li>
          <li>
            <a href=\"/Catshow\">
              <i class=\"bi bi-circle\"></i><span>Categorie</span>
            </a>
          </li>
        </ul>
      </li><!-- End Tables Nav -->

      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#charts-nav\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-bar-chart\"></i><span>mission Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"charts-nav\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"charts-chartjs.html\">
              <i class=\"bi bi-circle\"></i><span>mission</span>
            </a>
          </li>
          <li>
            <a href=\"charts-apexcharts.html\">
              <i class=\"bi bi-circle\"></i><span>recompense</span>
            </a>
          </li>
        </ul>
      </li><!-- End Charts Nav -->
      <li class=\"nav-item\">
        <a class=\"nav-link collapsed\" data-bs-target=\"#charts-navvvv\" data-bs-toggle=\"collapse\" href=\"#\">
          <i class=\"bi bi-bar-chart\"></i><span>Reclamtion Module</span><i class=\"bi bi-chevron-down ms-auto\"></i>
        </a>
        <ul id=\"charts-navvvv\" class=\"nav-content collapse \" data-bs-parent=\"#sidebar-nav\">
          <li>
            <a href=\"/reclamation/back\">
              <i class=\"bi bi-circle\"></i><span>Reclamation</span>
            </a>
          </li>
          <li>
            <a href=\"charts-apexcharts.html\">
              <i class=\"bi bi-circle\"></i><span>Reponse</span>
            </a>
          </li>
        </ul>
      </li>
      </li><!-- End Profile Page Nav -->

  </aside><!-- End Sidebar-->
  {% endblock %}
  {% block body %}
  <main id=\"main\" class=\"main\">

    <div class=\"pagetitle\">
      <h1>Dashboard</h1>
      <nav>
        <ol class=\"breadcrumb\">
          <li class=\"breadcrumb-item\"><a href=\"index.html\">Home</a></li>
          <li class=\"breadcrumb-item active\">Dashboard</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class=\"section dashboard\">
      <div class=\"row\">

        <!-- Left side columns -->
        <div class=\"col-lg-8\">
          <div class=\"row\">

            <!-- Sales Card -->
            <div class=\"col-xxl-4 col-md-6\">
              <div class=\"card info-card sales-card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Sales <span>| Today</span></h5>

                  <div class=\"d-flex align-items-center\">
                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">
                      <i class=\"bi bi-cart\"></i>
                    </div>
                    <div class=\"ps-3\">
                      <h6>145</h6>
                      <span class=\"text-success small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>

                    </div>
                  </div>
                </div>

              </div>
            </div><!-- End Sales Card -->

            <!-- Revenue Card -->
            <div class=\"col-xxl-4 col-md-6\">
              <div class=\"card info-card revenue-card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Revenue <span>| This Month</span></h5>

                  <div class=\"d-flex align-items-center\">
                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">
                      <i class=\"bi bi-currency-dollar\"></i>
                    </div>
                    <div class=\"ps-3\">
                      <h6>\$3,264</h6>
                      <span class=\"text-success small pt-1 fw-bold\">8%</span> <span class=\"text-muted small pt-2 ps-1\">increase</span>

                    </div>
                  </div>
                </div>

              </div>
            </div><!-- End Revenue Card -->

            <!-- Customers Card -->
            <div class=\"col-xxl-4 col-xl-12\">

              <div class=\"card info-card customers-card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Customers <span>| This Year</span></h5>

                  <div class=\"d-flex align-items-center\">
                    <div class=\"card-icon rounded-circle d-flex align-items-center justify-content-center\">
                      <i class=\"bi bi-people\"></i>
                    </div>
                    <div class=\"ps-3\">
                      <h6>1244</h6>
                      <span class=\"text-danger small pt-1 fw-bold\">12%</span> <span class=\"text-muted small pt-2 ps-1\">decrease</span>

                    </div>
                  </div>

                </div>
              </div>

            </div><!-- End Customers Card -->

            <!-- Reports -->
            <div class=\"col-12\">
              <div class=\"card\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Reports <span>/Today</span></h5>

                  <!-- Line Chart -->
                  <div id=\"reportsChart\"></div>

                  <script>
                    document.addEventListener(\"DOMContentLoaded\", () => {
                      new ApexCharts(document.querySelector(\"#reportsChart\"), {
                        series: [{
                          name: 'Sales',
                          data: [31, 40, 28, 51, 42, 82, 56],
                        }, {
                          name: 'Revenue',
                          data: [11, 32, 45, 32, 34, 52, 41]
                        }, {
                          name: 'Customers',
                          data: [15, 11, 32, 18, 9, 24, 11]
                        }],
                        chart: {
                          height: 350,
                          type: 'area',
                          toolbar: {
                            show: false
                          },
                        },
                        markers: {
                          size: 4
                        },
                        colors: ['#4154f1', '#2eca6a', '#ff771d'],
                        fill: {
                          type: \"gradient\",
                          gradient: {
                            shadeIntensity: 1,
                            opacityFrom: 0.3,
                            opacityTo: 0.4,
                            stops: [0, 90, 100]
                          }
                        },
                        dataLabels: {
                          enabled: false
                        },
                        stroke: {
                          curve: 'smooth',
                          width: 2
                        },
                        xaxis: {
                          type: 'datetime',
                          categories: [\"2018-09-19T00:00:00.000Z\", \"2018-09-19T01:30:00.000Z\", \"2018-09-19T02:30:00.000Z\", \"2018-09-19T03:30:00.000Z\", \"2018-09-19T04:30:00.000Z\", \"2018-09-19T05:30:00.000Z\", \"2018-09-19T06:30:00.000Z\"]
                        },
                        tooltip: {
                          x: {
                            format: 'dd/MM/yy HH:mm'
                          },
                        }
                      }).render();
                    });
                  </script>
                  <!-- End Line Chart -->

                </div>

              </div>
            </div><!-- End Reports -->

            <!-- Recent Sales -->
            <div class=\"col-12\">
              <div class=\"card recent-sales overflow-auto\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body\">
                  <h5 class=\"card-title\">Recent Sales <span>| Today</span></h5>

                  <table class=\"table table-borderless datatable\">
                    <thead>
                      <tr>
                        <th scope=\"col\">#</th>
                        <th scope=\"col\">Customer</th>
                        <th scope=\"col\">Product</th>
                        <th scope=\"col\">Price</th>
                        <th scope=\"col\">Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2457</a></th>
                        <td>Brandon Jacob</td>
                        <td><a href=\"#\" class=\"text-primary\">At praesentium minu</a></td>
                        <td>\$64</td>
                        <td><span class=\"badge bg-success\">Approved</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2147</a></th>
                        <td>Bridie Kessler</td>
                        <td><a href=\"#\" class=\"text-primary\">Blanditiis dolor omnis similique</a></td>
                        <td>\$47</td>
                        <td><span class=\"badge bg-warning\">Pending</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2049</a></th>
                        <td>Ashleigh Langosh</td>
                        <td><a href=\"#\" class=\"text-primary\">At recusandae consectetur</a></td>
                        <td>\$147</td>
                        <td><span class=\"badge bg-success\">Approved</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2644</a></th>
                        <td>Angus Grady</td>
                        <td><a href=\"#\" class=\"text-primar\">Ut voluptatem id earum et</a></td>
                        <td>\$67</td>
                        <td><span class=\"badge bg-danger\">Rejected</span></td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\">#2644</a></th>
                        <td>Raheem Lehner</td>
                        <td><a href=\"#\" class=\"text-primary\">Sunt similique distinctio</a></td>
                        <td>\$165</td>
                        <td><span class=\"badge bg-success\">Approved</span></td>
                      </tr>
                    </tbody>
                  </table>

                </div>

              </div>
            </div><!-- End Recent Sales -->

            <!-- Top Selling -->
            <div class=\"col-12\">
              <div class=\"card top-selling overflow-auto\">

                <div class=\"filter\">
                  <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
                  <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                    <li class=\"dropdown-header text-start\">
                      <h6>Filter</h6>
                    </li>

                    <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                    <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
                  </ul>
                </div>

                <div class=\"card-body pb-0\">
                  <h5 class=\"card-title\">Top Selling <span>| Today</span></h5>

                  <table class=\"table table-borderless\">
                    <thead>
                      <tr>
                        <th scope=\"col\">Preview</th>
                        <th scope=\"col\">Product</th>
                        <th scope=\"col\">Price</th>
                        <th scope=\"col\">Sold</th>
                        <th scope=\"col\">Revenue</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"{{asset('backTemp/img/product-1.jpg')}}\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Ut inventore ipsa voluptas nulla</a></td>
                        <td>\$64</td>
                        <td class=\"fw-bold\">124</td>
                        <td>\$5,828</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"{{asset('backTemp/img/product-2.jpg')}}\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Exercitationem similique doloremque</a></td>
                        <td>\$46</td>
                        <td class=\"fw-bold\">98</td>
                        <td>\$4,508</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"{{asset('backTemp/img/product-3.jpg')}}\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Doloribus nisi exercitationem</a></td>
                        <td>\$59</td>
                        <td class=\"fw-bold\">74</td>
                        <td>\$4,366</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"{{asset('backTemp/img/product-4.jpg')}}\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Officiis quaerat sint rerum error</a></td>
                        <td>\$32</td>
                        <td class=\"fw-bold\">63</td>
                        <td>\$2,016</td>
                      </tr>
                      <tr>
                        <th scope=\"row\"><a href=\"#\"><img src=\"{{asset('backTemp/img/product-5.jpg')}}\" alt=\"\"></a></th>
                        <td><a href=\"#\" class=\"text-primary fw-bold\">Sit unde debitis delectus repellendus</a></td>
                        <td>\$79</td>
                        <td class=\"fw-bold\">41</td>
                        <td>\$3,239</td>
                      </tr>
                    </tbody>
                  </table>

                </div>

              </div>
            </div><!-- End Top Selling -->

          </div>
        </div><!-- End Left side columns -->

        <!-- Right side columns -->
        <div class=\"col-lg-4\">

          <!-- Recent Activity -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body\">
              <h5 class=\"card-title\">Recent Activity <span>| Today</span></h5>

              <div class=\"activity\">

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">32 min</div>
                  <i class='bi bi-circle-fill activity-badge text-success align-self-start'></i>
                  <div class=\"activity-content\">
                    Quia quae rerum <a href=\"#\" class=\"fw-bold text-dark\">explicabo officiis</a> beatae
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">56 min</div>
                  <i class='bi bi-circle-fill activity-badge text-danger align-self-start'></i>
                  <div class=\"activity-content\">
                    Voluptatem blanditiis blanditiis eveniet
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">2 hrs</div>
                  <i class='bi bi-circle-fill activity-badge text-primary align-self-start'></i>
                  <div class=\"activity-content\">
                    Voluptates corrupti molestias voluptatem
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">1 day</div>
                  <i class='bi bi-circle-fill activity-badge text-info align-self-start'></i>
                  <div class=\"activity-content\">
                    Tempore autem saepe <a href=\"#\" class=\"fw-bold text-dark\">occaecati voluptatem</a> tempore
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">2 days</div>
                  <i class='bi bi-circle-fill activity-badge text-warning align-self-start'></i>
                  <div class=\"activity-content\">
                    Est sit eum reiciendis exercitationem
                  </div>
                </div><!-- End activity item-->

                <div class=\"activity-item d-flex\">
                  <div class=\"activite-label\">4 weeks</div>
                  <i class='bi bi-circle-fill activity-badge text-muted align-self-start'></i>
                  <div class=\"activity-content\">
                    Dicta dolorem harum nulla eius. Ut quidem quidem sit quas
                  </div>
                </div><!-- End activity item-->

              </div>

            </div>
          </div><!-- End Recent Activity -->

          <!-- Budget Report -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body pb-0\">
              <h5 class=\"card-title\">Budget Report <span>| This Month</span></h5>

              <div id=\"budgetChart\" style=\"min-height: 400px;\" class=\"echart\"></div>

              <script>
                document.addEventListener(\"DOMContentLoaded\", () => {
                  var budgetChart = echarts.init(document.querySelector(\"#budgetChart\")).setOption({
                    legend: {
                      data: ['Allocated Budget', 'Actual Spending']
                    },
                    radar: {
                      // shape: 'circle',
                      indicator: [{
                          name: 'Sales',
                          max: 6500
                        },
                        {
                          name: 'Administration',
                          max: 16000
                        },
                        {
                          name: 'Information Technology',
                          max: 30000
                        },
                        {
                          name: 'Customer Support',
                          max: 38000
                        },
                        {
                          name: 'Development',
                          max: 52000
                        },
                        {
                          name: 'Marketing',
                          max: 25000
                        }
                      ]
                    },
                    series: [{
                      name: 'Budget vs spending',
                      type: 'radar',
                      data: [{
                          value: [4200, 3000, 20000, 35000, 50000, 18000],
                          name: 'Allocated Budget'
                        },
                        {
                          value: [5000, 14000, 28000, 26000, 42000, 21000],
                          name: 'Actual Spending'
                        }
                      ]
                    }]
                  });
                });
              </script>

            </div>
          </div><!-- End Budget Report -->

          <!-- Website Traffic -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body pb-0\">
              <h5 class=\"card-title\">Website Traffic <span>| Today</span></h5>

              <div id=\"trafficChart\" style=\"min-height: 400px;\" class=\"echart\"></div>

              <script>
                document.addEventListener(\"DOMContentLoaded\", () => {
                  echarts.init(document.querySelector(\"#trafficChart\")).setOption({
                    tooltip: {
                      trigger: 'item'
                    },
                    legend: {
                      top: '5%',
                      left: 'center'
                    },
                    series: [{
                      name: 'Access From',
                      type: 'pie',
                      radius: ['40%', '70%'],
                      avoidLabelOverlap: false,
                      label: {
                        show: false,
                        position: 'center'
                      },
                      emphasis: {
                        label: {
                          show: true,
                          fontSize: '18',
                          fontWeight: 'bold'
                        }
                      },
                      labelLine: {
                        show: false
                      },
                      data: [{
                          value: 1048,
                          name: 'Search Engine'
                        },
                        {
                          value: 735,
                          name: 'Direct'
                        },
                        {
                          value: 580,
                          name: 'Email'
                        },
                        {
                          value: 484,
                          name: 'Union Ads'
                        },
                        {
                          value: 300,
                          name: 'Video Ads'
                        }
                      ]
                    }]
                  });
                });
              </script>

            </div>
          </div><!-- End Website Traffic -->

          <!-- News & Updates Traffic -->
          <div class=\"card\">
            <div class=\"filter\">
              <a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>
              <ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">
                <li class=\"dropdown-header text-start\">
                  <h6>Filter</h6>
                </li>

                <li><a class=\"dropdown-item\" href=\"#\">Today</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Month</a></li>
                <li><a class=\"dropdown-item\" href=\"#\">This Year</a></li>
              </ul>
            </div>

            <div class=\"card-body pb-0\">
              <h5 class=\"card-title\">News &amp; Updates <span>| Today</span></h5>

              <div class=\"news\">
                <div class=\"post-item clearfix\">
                  <img src=\"{{asset('backTemp/img/news-1.jpg')}}\" alt=\"\">
                  <h4><a href=\"#\">Nihil blanditiis at in nihil autem</a></h4>
                  <p>Sit recusandae non aspernatur laboriosam. Quia enim eligendi sed ut harum...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"{{asset('backTemp/img/news-2.jpg')}}\" alt=\"\">
                  <h4><a href=\"#\">Quidem autem et impedit</a></h4>
                  <p>Illo nemo neque maiores vitae officiis cum eum turos elan dries werona nande...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"{{asset('backTemp/img/news-3.jpg')}}\" alt=\"\">
                  <h4><a href=\"#\">Id quia et et ut maxime similique occaecati ut</a></h4>
                  <p>Fugiat voluptas vero eaque accusantium eos. Consequuntur sed ipsam et totam...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"{{asset('backTemp/img/news-4.jpg')}}\" alt=\"\">
                  <h4><a href=\"#\">Laborum corporis quo dara net para</a></h4>
                  <p>Qui enim quia optio. Eligendi aut asperiores enim repellendusvel rerum cuder...</p>
                </div>

                <div class=\"post-item clearfix\">
                  <img src=\"{{asset('backTemp/img/news-5.jpg')}}\" alt=\"\">
                  <h4><a href=\"#\">Et dolores corrupti quae illo quod dolor</a></h4>
                  <p>Odit ut eveniet modi reiciendis. Atque cupiditate libero beatae dignissimos eius...</p>
                </div>

              </div><!-- End sidebar recent posts-->

            </div>
          </div><!-- End News & Updates -->

        </div><!-- End Right side columns -->

      </div>
    </section>
{% endblock %}
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id=\"footer\" class=\"footer\">
    <div class=\"copyright\">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class=\"credits\">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href=\"https://bootstrapmade.com/\">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>
{% block js %}
  <!-- Vendor JS Files -->
  <script src=\"{{asset('backTemp/vendor/apexcharts/apexcharts.min.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/bootstrap/js/bootstrap.bundle.min.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/chart.js/chart.umd.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/echarts/echarts.min.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/quill/quill.min.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/simple-datatables/simple-datatables.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/tinymce/tinymce.min.js')}}\"></script>
  <script src=\"{{asset('backTemp/vendor/php-email-form/validate.js')}}\"></script>

  <!-- Template Main JS File -->
  <script src=\"{{asset('backTemp/js/main.js')}}\"></script>
{% endblock %}
</body>

</html>", "base-back.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\base-back.html.twig");
    }
}
