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

/* profile/edit.html.twig */
class __TwigTemplate_1b69294365d8c5871b9ebbeed9ccdf6d extends Template
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

        $this->blocks = [
            'title' => [$this, 'block_title'],
            'body' => [$this, 'block_body'],
        ];
    }

    protected function doGetParent(array $context): bool|string|Template|TemplateWrapper
    {
        // line 1
        return "base-back.html.twig";
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "profile/edit.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "profile/edit.html.twig"));

        $this->parent = $this->loadTemplate("base-back.html.twig", "profile/edit.html.twig", 1);
        yield from $this->parent->unwrap()->yield($context, array_merge($this->blocks, $blocks));
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

    }

    // line 3
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

        yield "Edit Profile - TrekSwap";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 5
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

        // line 6
        yield "<main id=\"main\" class=\"main\">
    <div class=\"pagetitle\">
        <h1>Profile</h1>
        <nav>
            <ol class=\"breadcrumb\">
                <li class=\"breadcrumb-item\"><a href=\"";
        // line 11
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_Abonnements");
        yield "\">Home</a></li>
                <li class=\"breadcrumb-item active\">Profile</li>
            </ol>
        </nav>
    </div>

    <section class=\"section profile\">
        <div class=\"row\">
            <div class=\"col-xl-4\">
                <div class=\"card\">
                    <div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">
                        ";
        // line 22
        if (CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 22, $this->source); })()), "user", [], "any", false, false, false, 22), "photoProfile", [], "any", false, false, false, 22)) {
            // line 23
            yield "                            <img src=\"";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/profile_pictures/" . CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 23, $this->source); })()), "user", [], "any", false, false, false, 23), "photoProfile", [], "any", false, false, false, 23))), "html", null, true);
            yield "\" alt=\"Profile\" class=\"rounded-circle\">
                        ";
        } else {
            // line 25
            yield "                            <img src=\"";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("backTemp/img/profile-img.jpg"), "html", null, true);
            yield "\" alt=\"Profile\" class=\"rounded-circle\">
                        ";
        }
        // line 27
        yield "                        <h2>";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 27, $this->source); })()), "user", [], "any", false, false, false, 27), "prenom", [], "any", false, false, false, 27), "html", null, true);
        yield " ";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 27, $this->source); })()), "user", [], "any", false, false, false, 27), "nom", [], "any", false, false, false, 27), "html", null, true);
        yield "</h2>
                        <h3>";
        // line 28
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 28, $this->source); })()), "user", [], "any", false, false, false, 28), "typeUser", [], "any", false, false, false, 28), "html", null, true);
        yield "</h3>
                    </div>
                </div>
            </div>

            <div class=\"col-xl-8\">
                <div class=\"card\">
                    <div class=\"card-body pt-3\">
                        ";
        // line 36
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable(CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 36, $this->source); })()), "flashes", ["success"], "method", false, false, false, 36));
        foreach ($context['_seq'] as $context["_key"] => $context["message"]) {
            // line 37
            yield "                            <div class=\"alert alert-success\">
                                ";
            // line 38
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($context["message"], "html", null, true);
            yield "
                            </div>
                        ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['message'], $context['_parent']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 41
        yield "                        ";
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable(CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 41, $this->source); })()), "flashes", ["error"], "method", false, false, false, 41));
        foreach ($context['_seq'] as $context["_key"] => $context["message"]) {
            // line 42
            yield "                            <div class=\"alert alert-danger\">
                                ";
            // line 43
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($context["message"], "html", null, true);
            yield "
                            </div>
                        ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['message'], $context['_parent']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 46
        yield "
                        ";
        // line 47
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 47, $this->source); })()), 'form_start', ["attr" => ["enctype" => "multipart/form-data"]]);
        yield "
                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">First Name</label>
                            <div class=\"col-md-8 col-lg-9\">
                                ";
        // line 51
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 51, $this->source); })()), "prenom", [], "any", false, false, false, 51), 'widget');
        yield "
                                ";
        // line 52
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 52, $this->source); })()), "prenom", [], "any", false, false, false, 52), 'errors');
        yield "
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">Last Name</label>
                            <div class=\"col-md-8 col-lg-9\">
                                ";
        // line 59
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 59, $this->source); })()), "nom", [], "any", false, false, false, 59), 'widget');
        yield "
                                ";
        // line 60
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 60, $this->source); })()), "nom", [], "any", false, false, false, 60), 'errors');
        yield "
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">Email</label>
                            <div class=\"col-md-8 col-lg-9\">
                                ";
        // line 67
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 67, $this->source); })()), "email", [], "any", false, false, false, 67), 'widget');
        yield "
                                ";
        // line 68
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 68, $this->source); })()), "email", [], "any", false, false, false, 68), 'errors');
        yield "
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">New Password</label>
                            <div class=\"col-md-8 col-lg-9\">
                                ";
        // line 75
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 75, $this->source); })()), "plainPassword", [], "any", false, false, false, 75), 'widget');
        yield "
                                ";
        // line 76
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 76, $this->source); })()), "plainPassword", [], "any", false, false, false, 76), 'errors');
        yield "
                                <div class=\"form-text\">Leave empty to keep current password</div>
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">Profile Image</label>
                            <div class=\"col-md-8 col-lg-9\">
                                ";
        // line 84
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 84, $this->source); })()), "photo_profil", [], "any", false, false, false, 84), 'widget');
        yield "
                                ";
        // line 85
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 85, $this->source); })()), "photo_profil", [], "any", false, false, false, 85), 'errors');
        yield "
                            </div>
                        </div>

                        <div class=\"text-center\">
                            <button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>
                            ";
        // line 91
        if ((CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 91, $this->source); })()), "user", [], "any", false, false, false, 91), "typeUser", [], "any", false, false, false, 91) != "Admin")) {
            // line 92
            yield "                                <button type=\"button\" class=\"btn btn-danger ms-2\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteAccountModal\">
                                    Delete Account
                                </button>
                            ";
        }
        // line 96
        yield "                        </div>
                        ";
        // line 97
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["profileForm"]) || array_key_exists("profileForm", $context) ? $context["profileForm"] : (function () { throw new RuntimeError('Variable "profileForm" does not exist.', 97, $this->source); })()), 'form_end');
        yield "
                    </div>
                </div>
            </div>
        </div>
    </section>

    ";
        // line 104
        if ((CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 104, $this->source); })()), "user", [], "any", false, false, false, 104), "typeUser", [], "any", false, false, false, 104) != "Admin")) {
            // line 105
            yield "    <!-- Delete Account Modal -->
    <div class=\"modal fade\" id=\"deleteAccountModal\" tabindex=\"-1\">
        <div class=\"modal-dialog\">
            <div class=\"modal-content\">
                <div class=\"modal-header\">
                    <h5 class=\"modal-title\">Delete Account</h5>
                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>
                </div>
                <div class=\"modal-body\">
                    <p>Are you sure you want to delete your account? This action cannot be undone.</p>
                </div>
                <div class=\"modal-footer\">
                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>
                    <form action=\"";
            // line 118
            yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_profile_delete");
            yield "\" method=\"post\" style=\"display: inline;\">
                        <input type=\"hidden\" name=\"_token\" value=\"";
            // line 119
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken("delete-profile"), "html", null, true);
            yield "\">
                        <button type=\"submit\" class=\"btn btn-danger\">Delete Account</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    ";
        }
        // line 127
        yield "</main>
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
        return "profile/edit.html.twig";
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
        return array (  319 => 127,  308 => 119,  304 => 118,  289 => 105,  287 => 104,  277 => 97,  274 => 96,  268 => 92,  266 => 91,  257 => 85,  253 => 84,  242 => 76,  238 => 75,  228 => 68,  224 => 67,  214 => 60,  210 => 59,  200 => 52,  196 => 51,  189 => 47,  186 => 46,  177 => 43,  174 => 42,  169 => 41,  160 => 38,  157 => 37,  153 => 36,  142 => 28,  135 => 27,  129 => 25,  123 => 23,  121 => 22,  107 => 11,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base-back.html.twig' %}

{% block title %}Edit Profile - TrekSwap{% endblock %}

{% block body %}
<main id=\"main\" class=\"main\">
    <div class=\"pagetitle\">
        <h1>Profile</h1>
        <nav>
            <ol class=\"breadcrumb\">
                <li class=\"breadcrumb-item\"><a href=\"{{ path('app_Abonnements') }}\">Home</a></li>
                <li class=\"breadcrumb-item active\">Profile</li>
            </ol>
        </nav>
    </div>

    <section class=\"section profile\">
        <div class=\"row\">
            <div class=\"col-xl-4\">
                <div class=\"card\">
                    <div class=\"card-body profile-card pt-4 d-flex flex-column align-items-center\">
                        {% if app.user.photoProfile %}
                            <img src=\"{{ asset('uploads/profile_pictures/' ~ app.user.photoProfile) }}\" alt=\"Profile\" class=\"rounded-circle\">
                        {% else %}
                            <img src=\"{{asset('backTemp/img/profile-img.jpg')}}\" alt=\"Profile\" class=\"rounded-circle\">
                        {% endif %}
                        <h2>{{ app.user.prenom }} {{ app.user.nom }}</h2>
                        <h3>{{ app.user.typeUser }}</h3>
                    </div>
                </div>
            </div>

            <div class=\"col-xl-8\">
                <div class=\"card\">
                    <div class=\"card-body pt-3\">
                        {% for message in app.flashes('success') %}
                            <div class=\"alert alert-success\">
                                {{ message }}
                            </div>
                        {% endfor %}
                        {% for message in app.flashes('error') %}
                            <div class=\"alert alert-danger\">
                                {{ message }}
                            </div>
                        {% endfor %}

                        {{ form_start(profileForm, {'attr': {'enctype': 'multipart/form-data'}}) }}
                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">First Name</label>
                            <div class=\"col-md-8 col-lg-9\">
                                {{ form_widget(profileForm.prenom) }}
                                {{ form_errors(profileForm.prenom) }}
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">Last Name</label>
                            <div class=\"col-md-8 col-lg-9\">
                                {{ form_widget(profileForm.nom) }}
                                {{ form_errors(profileForm.nom) }}
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">Email</label>
                            <div class=\"col-md-8 col-lg-9\">
                                {{ form_widget(profileForm.email) }}
                                {{ form_errors(profileForm.email) }}
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">New Password</label>
                            <div class=\"col-md-8 col-lg-9\">
                                {{ form_widget(profileForm.plainPassword) }}
                                {{ form_errors(profileForm.plainPassword) }}
                                <div class=\"form-text\">Leave empty to keep current password</div>
                            </div>
                        </div>

                        <div class=\"row mb-3\">
                            <label class=\"col-md-4 col-lg-3 col-form-label\">Profile Image</label>
                            <div class=\"col-md-8 col-lg-9\">
                                {{ form_widget(profileForm.photo_profil) }}
                                {{ form_errors(profileForm.photo_profil) }}
                            </div>
                        </div>

                        <div class=\"text-center\">
                            <button type=\"submit\" class=\"btn btn-primary\">Save Changes</button>
                            {% if app.user.typeUser != 'Admin' %}
                                <button type=\"button\" class=\"btn btn-danger ms-2\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteAccountModal\">
                                    Delete Account
                                </button>
                            {% endif %}
                        </div>
                        {{ form_end(profileForm) }}
                    </div>
                </div>
            </div>
        </div>
    </section>

    {% if app.user.typeUser != 'Admin' %}
    <!-- Delete Account Modal -->
    <div class=\"modal fade\" id=\"deleteAccountModal\" tabindex=\"-1\">
        <div class=\"modal-dialog\">
            <div class=\"modal-content\">
                <div class=\"modal-header\">
                    <h5 class=\"modal-title\">Delete Account</h5>
                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>
                </div>
                <div class=\"modal-body\">
                    <p>Are you sure you want to delete your account? This action cannot be undone.</p>
                </div>
                <div class=\"modal-footer\">
                    <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Cancel</button>
                    <form action=\"{{ path('app_profile_delete') }}\" method=\"post\" style=\"display: inline;\">
                        <input type=\"hidden\" name=\"_token\" value=\"{{ csrf_token('delete-profile') }}\">
                        <button type=\"submit\" class=\"btn btn-danger\">Delete Account</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    {% endif %}
</main>
{% endblock %} ", "profile/edit.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\profile\\edit.html.twig");
    }
}
