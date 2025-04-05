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

/* security/login.html.twig */
class __TwigTemplate_c2562c6cad4f829bf128d4aed68c4778 extends Template
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
        return "base.html.twig";
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "security/login.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "security/login.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "security/login.html.twig", 1);
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

        yield "Log in - TrekSwap";
        
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
        yield "<section class=\"vh-100\" style=\"padding-top: 100px;\">
    <div class=\"container py-5 h-100\">
        <div class=\"row d-flex justify-content-center align-items-center h-100\">
            <div class=\"col-12 col-md-8 col-lg-6 col-xl-5\">
                <div class=\"card shadow-lg\" style=\"border-radius: 1rem;\">
                    <div class=\"card-body p-5 text-center\">
                        <form method=\"post\">
                            ";
        // line 13
        if ((isset($context["error"]) || array_key_exists("error", $context) ? $context["error"] : (function () { throw new RuntimeError('Variable "error" does not exist.', 13, $this->source); })())) {
            // line 14
            yield "                                <div class=\"alert alert-danger\">";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\TranslationExtension']->trans(CoreExtension::getAttribute($this->env, $this->source, (isset($context["error"]) || array_key_exists("error", $context) ? $context["error"] : (function () { throw new RuntimeError('Variable "error" does not exist.', 14, $this->source); })()), "messageKey", [], "any", false, false, false, 14), CoreExtension::getAttribute($this->env, $this->source, (isset($context["error"]) || array_key_exists("error", $context) ? $context["error"] : (function () { throw new RuntimeError('Variable "error" does not exist.', 14, $this->source); })()), "messageData", [], "any", false, false, false, 14), "security"), "html", null, true);
            yield "</div>
                            ";
        }
        // line 16
        yield "
                            <h3 class=\"mb-5\">Sign in</h3>

                            <div class=\"form-outline mb-4\">
                                <input type=\"email\" value=\"";
        // line 20
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["last_username"]) || array_key_exists("last_username", $context) ? $context["last_username"] : (function () { throw new RuntimeError('Variable "last_username" does not exist.', 20, $this->source); })()), "html", null, true);
        yield "\" name=\"email\" id=\"inputEmail\" class=\"form-control form-control-lg\" placeholder=\"Email\" required autofocus>
                            </div>

                            <div class=\"form-outline mb-4\">
                                <input type=\"password\" name=\"password\" id=\"inputPassword\" class=\"form-control form-control-lg\" placeholder=\"Password\" required>
                            </div>

                            <input type=\"hidden\" name=\"_csrf_token\" value=\"";
        // line 27
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken("authenticate"), "html", null, true);
        yield "\">

                            <div class=\"form-check d-flex justify-content-start mb-4\">
                                <input class=\"form-check-input\" type=\"checkbox\" name=\"_remember_me\" id=\"remember_me\">
                                <label class=\"form-check-label ms-2\" for=\"remember_me\"> Remember me </label>
                            </div>

                            <button class=\"btn btn-primary btn-lg btn-block\" type=\"submit\">Login</button>

                            <hr class=\"my-4\">

                            <div>
                                <p class=\"mb-0\">Don't have an account? <a href=\"";
        // line 39
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_register");
        yield "\" class=\"text-primary fw-bold\">Sign Up</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
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
        return "security/login.html.twig";
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
        return array (  148 => 39,  133 => 27,  123 => 20,  117 => 16,  111 => 14,  109 => 13,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Log in - TrekSwap{% endblock %}

{% block body %}
<section class=\"vh-100\" style=\"padding-top: 100px;\">
    <div class=\"container py-5 h-100\">
        <div class=\"row d-flex justify-content-center align-items-center h-100\">
            <div class=\"col-12 col-md-8 col-lg-6 col-xl-5\">
                <div class=\"card shadow-lg\" style=\"border-radius: 1rem;\">
                    <div class=\"card-body p-5 text-center\">
                        <form method=\"post\">
                            {% if error %}
                                <div class=\"alert alert-danger\">{{ error.messageKey|trans(error.messageData, 'security') }}</div>
                            {% endif %}

                            <h3 class=\"mb-5\">Sign in</h3>

                            <div class=\"form-outline mb-4\">
                                <input type=\"email\" value=\"{{ last_username }}\" name=\"email\" id=\"inputEmail\" class=\"form-control form-control-lg\" placeholder=\"Email\" required autofocus>
                            </div>

                            <div class=\"form-outline mb-4\">
                                <input type=\"password\" name=\"password\" id=\"inputPassword\" class=\"form-control form-control-lg\" placeholder=\"Password\" required>
                            </div>

                            <input type=\"hidden\" name=\"_csrf_token\" value=\"{{ csrf_token('authenticate') }}\">

                            <div class=\"form-check d-flex justify-content-start mb-4\">
                                <input class=\"form-check-input\" type=\"checkbox\" name=\"_remember_me\" id=\"remember_me\">
                                <label class=\"form-check-label ms-2\" for=\"remember_me\"> Remember me </label>
                            </div>

                            <button class=\"btn btn-primary btn-lg btn-block\" type=\"submit\">Login</button>

                            <hr class=\"my-4\">

                            <div>
                                <p class=\"mb-0\">Don't have an account? <a href=\"{{ path('app_register') }}\" class=\"text-primary fw-bold\">Sign Up</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
{% endblock %} ", "security/login.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\security\\login.html.twig");
    }
}
