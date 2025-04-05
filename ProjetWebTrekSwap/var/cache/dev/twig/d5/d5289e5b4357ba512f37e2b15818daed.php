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

/* admin/index.html.twig */
class __TwigTemplate_a4f9a32959678e20c8b7773604f74991 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "admin/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "admin/index.html.twig"));

        $this->parent = $this->loadTemplate("base-back.html.twig", "admin/index.html.twig", 1);
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

        yield "Admin Dashboard - TrekSwap";
        
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
        <h1>Admin Dashboard</h1>
        <nav>
            <ol class=\"breadcrumb\">
                <li class=\"breadcrumb-item\"><a href=\"";
        // line 11
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_admin");
        yield "\">Home</a></li>
                <li class=\"breadcrumb-item active\">Admin Dashboard</li>
            </ol>
        </nav>
    </div>

    <section class=\"section\">
        <div class=\"row\">
            <div class=\"col-lg-12\">
                <div class=\"card\">
                    <div class=\"card-body\">
                        <h5 class=\"card-title\">Users Management</h5>

                        <!-- Users Table -->
                        <table class=\"table table-striped\">
                            <thead>
                                <tr>
                                    <th scope=\"col\">#</th>
                                    <th scope=\"col\">Name</th>
                                    <th scope=\"col\">Email</th>
                                    <th scope=\"col\">Type</th>
                                    <th scope=\"col\">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                ";
        // line 36
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["users"]) || array_key_exists("users", $context) ? $context["users"] : (function () { throw new RuntimeError('Variable "users" does not exist.', 36, $this->source); })()));
        foreach ($context['_seq'] as $context["_key"] => $context["user"]) {
            // line 37
            yield "                                    <tr>
                                        <th scope=\"row\">";
            // line 38
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["user"], "id", [], "any", false, false, false, 38), "html", null, true);
            yield "</th>
                                        <td>";
            // line 39
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["user"], "prenom", [], "any", false, false, false, 39), "html", null, true);
            yield " ";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["user"], "nom", [], "any", false, false, false, 39), "html", null, true);
            yield "</td>
                                        <td>";
            // line 40
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["user"], "email", [], "any", false, false, false, 40), "html", null, true);
            yield "</td>
                                        <td>
                                            <span class=\"badge bg-";
            // line 42
            yield (((CoreExtension::getAttribute($this->env, $this->source, $context["user"], "typeUser", [], "any", false, false, false, 42) == "Admin")) ? ("primary") : ("success"));
            yield "\">
                                                ";
            // line 43
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["user"], "typeUser", [], "any", false, false, false, 43), "html", null, true);
            yield "
                                            </span>
                                        </td>
                                        <td>
                                            ";
            // line 47
            if ((CoreExtension::getAttribute($this->env, $this->source, $context["user"], "typeUser", [], "any", false, false, false, 47) != "Admin")) {
                // line 48
                yield "                                                <button class=\"btn btn-sm btn-danger\">Delete</button>
                                            ";
            }
            // line 50
            yield "                                        </td>
                                    </tr>
                                ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['user'], $context['_parent']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 53
        yield "                            </tbody>
                        </table>
                        <!-- End Users Table -->

                    </div>
                </div>
            </div>
        </div>
    </section>

</main>
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
        return "admin/index.html.twig";
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
        return array (  182 => 53,  174 => 50,  170 => 48,  168 => 47,  161 => 43,  157 => 42,  152 => 40,  146 => 39,  142 => 38,  139 => 37,  135 => 36,  107 => 11,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base-back.html.twig' %}

{% block title %}Admin Dashboard - TrekSwap{% endblock %}

{% block body %}
<main id=\"main\" class=\"main\">
    <div class=\"pagetitle\">
        <h1>Admin Dashboard</h1>
        <nav>
            <ol class=\"breadcrumb\">
                <li class=\"breadcrumb-item\"><a href=\"{{ path('app_admin') }}\">Home</a></li>
                <li class=\"breadcrumb-item active\">Admin Dashboard</li>
            </ol>
        </nav>
    </div>

    <section class=\"section\">
        <div class=\"row\">
            <div class=\"col-lg-12\">
                <div class=\"card\">
                    <div class=\"card-body\">
                        <h5 class=\"card-title\">Users Management</h5>

                        <!-- Users Table -->
                        <table class=\"table table-striped\">
                            <thead>
                                <tr>
                                    <th scope=\"col\">#</th>
                                    <th scope=\"col\">Name</th>
                                    <th scope=\"col\">Email</th>
                                    <th scope=\"col\">Type</th>
                                    <th scope=\"col\">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {% for user in users %}
                                    <tr>
                                        <th scope=\"row\">{{ user.id }}</th>
                                        <td>{{ user.prenom }} {{ user.nom }}</td>
                                        <td>{{ user.email }}</td>
                                        <td>
                                            <span class=\"badge bg-{{ user.typeUser == 'Admin' ? 'primary' : 'success' }}\">
                                                {{ user.typeUser }}
                                            </span>
                                        </td>
                                        <td>
                                            {% if user.typeUser != 'Admin' %}
                                                <button class=\"btn btn-sm btn-danger\">Delete</button>
                                            {% endif %}
                                        </td>
                                    </tr>
                                {% endfor %}
                            </tbody>
                        </table>
                        <!-- End Users Table -->

                    </div>
                </div>
            </div>
        </div>
    </section>

</main>
{% endblock %} ", "admin/index.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\admin\\index.html.twig");
    }
}
