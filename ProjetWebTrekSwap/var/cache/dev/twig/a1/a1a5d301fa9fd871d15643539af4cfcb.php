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

/* destination/showFront.html.twig */
class __TwigTemplate_f8cd36f3eab572320866153ad254f5d2 extends Template
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
            'welcome' => [$this, 'block_welcome'],
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "destination/showFront.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "destination/showFront.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "destination/showFront.html.twig", 1);
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

        yield "Destinations";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 4
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_welcome(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "welcome"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "welcome"));

        // line 5
        yield "
";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 7
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

        // line 8
        yield "
    <style>
        .destination-card {
            position: relative;
            width: 100%;
            overflow: hidden;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out;
        }

        .destination-card:hover {
            transform: scale(1.05);
        }

        .destination-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
        }

        .destination-card-content {
            padding: 15px;
            background: white;
            text-align: center;
            border-bottom-left-radius: 15px;
            border-bottom-right-radius: 15px;
        }

        .destination-card h5 {
            font-size: 18px;
            font-weight: bold;
            margin: 10px 0;
        }

        .destination-card .details {
            font-size: 14px;
            color: #555;
        }

        /* Hover effect */
        .description-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 20px;
            opacity: 0;
            transition: opacity 0.4s ease-in-out;
            border-radius: 15px;
        }

        .destination-card:hover .description-overlay {
            opacity: 1;
        }

    </style>

    <main class=\"container py-5\">
        <h1 class=\"text-center mb-4\">Nos Destinations</h1>

        <div class=\"row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4\">
            ";
        // line 80
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["destinations"]) || array_key_exists("destinations", $context) ? $context["destinations"] : (function () { throw new RuntimeError('Variable "destinations" does not exist.', 80, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["destination"]) {
            // line 81
            yield "                <div class=\"col\">
                    <div class=\"destination-card\">
                        <img src=\"";
            // line 83
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "imageDestination", [], "any", false, false, false, 83), "html", null, true);
            yield "\" alt=\"";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "nomDestination", [], "any", false, false, false, 83), "html", null, true);
            yield "\">

                        <div class=\"description-overlay\">
                            <h5>";
            // line 86
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "nomDestination", [], "any", false, false, false, 86), "html", null, true);
            yield "</h5>
                            <p>";
            // line 87
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "description", [], "any", false, false, false, 87), "html", null, true);
            yield "</p>
                        </div>

                        <div class=\"destination-card-content\">
                            <h5>";
            // line 91
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "nomDestination", [], "any", false, false, false, 91), "html", null, true);
            yield "</h5>
                            <p class=\"details\">
                                <strong>Température:</strong> ";
            // line 93
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "temperature", [], "any", false, false, false, 93), "html", null, true);
            yield "°C<br>
                                <strong>Note:</strong> ";
            // line 94
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "rate", [], "any", false, false, false, 94), "html", null, true);
            yield "/5
                            </p>
                        </div>
                    </div>
                </div>
            ";
            $context['_iterated'] = true;
        }
        // line 99
        if (!$context['_iterated']) {
            // line 100
            yield "                <div class=\"col-12\">
                    <p class=\"text-center text-muted\">Aucune destination disponible pour le moment.</p>
                </div>
            ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['destination'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 104
        yield "        </div>
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
        return "destination/showFront.html.twig";
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
        return array (  258 => 104,  249 => 100,  247 => 99,  237 => 94,  233 => 93,  228 => 91,  221 => 87,  217 => 86,  209 => 83,  205 => 81,  200 => 80,  126 => 8,  113 => 7,  101 => 5,  88 => 4,  65 => 3,  42 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Destinations{% endblock %}
{% block welcome %}

{% endblock %}
{% block body %}

    <style>
        .destination-card {
            position: relative;
            width: 100%;
            overflow: hidden;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out;
        }

        .destination-card:hover {
            transform: scale(1.05);
        }

        .destination-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
        }

        .destination-card-content {
            padding: 15px;
            background: white;
            text-align: center;
            border-bottom-left-radius: 15px;
            border-bottom-right-radius: 15px;
        }

        .destination-card h5 {
            font-size: 18px;
            font-weight: bold;
            margin: 10px 0;
        }

        .destination-card .details {
            font-size: 14px;
            color: #555;
        }

        /* Hover effect */
        .description-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 20px;
            opacity: 0;
            transition: opacity 0.4s ease-in-out;
            border-radius: 15px;
        }

        .destination-card:hover .description-overlay {
            opacity: 1;
        }

    </style>

    <main class=\"container py-5\">
        <h1 class=\"text-center mb-4\">Nos Destinations</h1>

        <div class=\"row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4\">
            {% for destination in destinations %}
                <div class=\"col\">
                    <div class=\"destination-card\">
                        <img src=\"{{ destination.imageDestination }}\" alt=\"{{ destination.nomDestination }}\">

                        <div class=\"description-overlay\">
                            <h5>{{ destination.nomDestination }}</h5>
                            <p>{{ destination.description }}</p>
                        </div>

                        <div class=\"destination-card-content\">
                            <h5>{{ destination.nomDestination }}</h5>
                            <p class=\"details\">
                                <strong>Température:</strong> {{ destination.temperature }}°C<br>
                                <strong>Note:</strong> {{ destination.rate }}/5
                            </p>
                        </div>
                    </div>
                </div>
            {% else %}
                <div class=\"col-12\">
                    <p class=\"text-center text-muted\">Aucune destination disponible pour le moment.</p>
                </div>
            {% endfor %}
        </div>
    </main>


{% endblock %}
", "destination/showFront.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\destination\\showFront.html.twig");
    }
}
