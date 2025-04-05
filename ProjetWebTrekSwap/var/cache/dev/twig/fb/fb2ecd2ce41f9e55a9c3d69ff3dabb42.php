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

/* destination/index.html.twig */
class __TwigTemplate_a8968facb4a7e4682b9eed8c96db2a8d extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "destination/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "destination/index.html.twig"));

        $this->parent = $this->loadTemplate("base-back.html.twig", "destination/index.html.twig", 1);
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

        yield "listDestinationBack";
        
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
        yield "    <main id=\"main\" class=\"main\" >
        <div class=\"container\">
            <h1 class=\"mb-4\">Liste des destination</h1>
            <input type=\"text\" id=\"search-input\" placeholder=\"Search...\">
            <a href=\"";
        // line 10
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("addDestination");
        yield "\" class=\"btn btn-info btn-sm\">ajouter</a>
            ";
        // line 11
        if ((Twig\Extension\CoreExtension::length($this->env->getCharset(), (isset($context["destinations"]) || array_key_exists("destinations", $context) ? $context["destinations"] : (function () { throw new RuntimeError('Variable "destinations" does not exist.', 11, $this->source); })())) > 0)) {
            // line 12
            yield "                <table class=\"table table-striped\">
                    <thead>
                    <tr>
                        <th scope=\"col\">Id</th>
                        <th scope=\"col\">nom_dest</th>
                        <th scope=\"col\">description</th>
                        <th scope=\"col\">image_destination</th>
                        <th scope=\"col\">latitude</th>
                        <th scope=\"col\">longitude</th>
                        <th scope=\"col\">tremperature</th>
                        <th scope=\"col\">rate</th>
                        <th scope=\"col\">Actions</th>

                    </tr>
                    </thead>
                    <tbody>
                    ";
            // line 28
            $context['_parent'] = $context;
            $context['_seq'] = CoreExtension::ensureTraversable((isset($context["destinations"]) || array_key_exists("destinations", $context) ? $context["destinations"] : (function () { throw new RuntimeError('Variable "destinations" does not exist.', 28, $this->source); })()));
            foreach ($context['_seq'] as $context["_key"] => $context["destination"]) {
                // line 29
                yield "                        <tr>
                            <td>";
                // line 30
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "id", [], "any", false, false, false, 30), "html", null, true);
                yield "</td>
                            <td>";
                // line 31
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "nomDestination", [], "any", false, false, false, 31), "html", null, true);
                yield "</td>
                            <td>";
                // line 32
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "description", [], "any", false, false, false, 32), "html", null, true);
                yield "</td>
                            <td> <img src=\"";
                // line 33
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "imageDestination", [], "any", false, false, false, 33), "html", null, true);
                yield "\" alt=\"Destination Image\" width=\"150\"></td>
                            <td>";
                // line 34
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "latitude", [], "any", false, false, false, 34), "html", null, true);
                yield "</td>
                            <td>";
                // line 35
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "longitude", [], "any", false, false, false, 35), "html", null, true);
                yield "</td>
                            <td>";
                // line 36
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "temperature", [], "any", false, false, false, 36), "html", null, true);
                yield "</td>
                            <td>";
                // line 37
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "rate", [], "any", false, false, false, 37), "html", null, true);
                yield "</td>
                            <td>
                                <a href=\"";
                // line 39
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("editDest", ["id" => CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "id", [], "any", false, false, false, 39)]), "html", null, true);
                yield "\" class=\"btn btn-success btn-sm\">Modifier</a>
                                <a href=\"";
                // line 40
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("deleteDest", ["id" => CoreExtension::getAttribute($this->env, $this->source, $context["destination"], "id", [], "any", false, false, false, 40)]), "html", null, true);
                yield "\" class=\"btn btn-warning btn-sm\">Supprimer</a>
                            </td>

                        </tr>
                    ";
            }
            $_parent = $context['_parent'];
            unset($context['_seq'], $context['_key'], $context['destination'], $context['_parent']);
            $context = array_intersect_key($context, $_parent) + $_parent;
            // line 45
            yield "                    </tbody>
                </table>
            ";
        } else {
            // line 48
            yield "                <p class=\"text-muted\">Aucun destination trouvé.</p>
            ";
        }
        // line 50
        yield "


        </div>
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
        return "destination/index.html.twig";
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
        return array (  194 => 50,  190 => 48,  185 => 45,  174 => 40,  170 => 39,  165 => 37,  161 => 36,  157 => 35,  153 => 34,  149 => 33,  145 => 32,  141 => 31,  137 => 30,  134 => 29,  130 => 28,  112 => 12,  110 => 11,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base-back.html.twig' %}

{% block title %}listDestinationBack{% endblock %}

{% block body %}
    <main id=\"main\" class=\"main\" >
        <div class=\"container\">
            <h1 class=\"mb-4\">Liste des destination</h1>
            <input type=\"text\" id=\"search-input\" placeholder=\"Search...\">
            <a href=\"{{ path('addDestination') }}\" class=\"btn btn-info btn-sm\">ajouter</a>
            {% if destinations|length > 0 %}
                <table class=\"table table-striped\">
                    <thead>
                    <tr>
                        <th scope=\"col\">Id</th>
                        <th scope=\"col\">nom_dest</th>
                        <th scope=\"col\">description</th>
                        <th scope=\"col\">image_destination</th>
                        <th scope=\"col\">latitude</th>
                        <th scope=\"col\">longitude</th>
                        <th scope=\"col\">tremperature</th>
                        <th scope=\"col\">rate</th>
                        <th scope=\"col\">Actions</th>

                    </tr>
                    </thead>
                    <tbody>
                    {% for destination in destinations %}
                        <tr>
                            <td>{{ destination.id }}</td>
                            <td>{{ destination.nomDestination }}</td>
                            <td>{{ destination.description }}</td>
                            <td> <img src=\"{{ destination.imageDestination }}\" alt=\"Destination Image\" width=\"150\"></td>
                            <td>{{ destination.latitude }}</td>
                            <td>{{ destination.longitude }}</td>
                            <td>{{ destination.temperature }}</td>
                            <td>{{ destination.rate }}</td>
                            <td>
                                <a href=\"{{ path('editDest', {'id': destination.id}) }}\" class=\"btn btn-success btn-sm\">Modifier</a>
                                <a href=\"{{ path('deleteDest', {'id': destination.id}) }}\" class=\"btn btn-warning btn-sm\">Supprimer</a>
                            </td>

                        </tr>
                    {% endfor %}
                    </tbody>
                </table>
            {% else %}
                <p class=\"text-muted\">Aucun destination trouvé.</p>
            {% endif %}



        </div>
    </main>
{% endblock %}
", "destination/index.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\destination\\index.html.twig");
    }
}
