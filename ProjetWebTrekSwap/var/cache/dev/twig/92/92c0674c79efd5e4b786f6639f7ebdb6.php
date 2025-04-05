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

/* activite/list.html.twig */
class __TwigTemplate_26d6fa4ceec1436b54f443370aa09981 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "activite/list.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "activite/list.html.twig"));

        $this->parent = $this->loadTemplate("base-back.html.twig", "activite/list.html.twig", 1);
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

        yield "Liste des Activités";
        
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
        yield "    <main id=\"main\" class=\"main\">
        <div class=\"container\">
            <h1 class=\"mb-4\">Liste des Activités</h1>
            <input type=\"text\" id=\"search-input\" placeholder=\"Search...\">
            <a href=\"";
        // line 10
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("add_activite");
        yield "\" class=\"btn btn-info btn-sm\">Ajouter</a>

            ";
        // line 12
        if ((Twig\Extension\CoreExtension::length($this->env->getCharset(), (isset($context["activites"]) || array_key_exists("activites", $context) ? $context["activites"] : (function () { throw new RuntimeError('Variable "activites" does not exist.', 12, $this->source); })())) > 0)) {
            // line 13
            yield "                <table class=\"table table-striped\">
                    <thead>
                    <tr>
                        <th scope=\"col\">Id</th>
                        <th scope=\"col\">Nom Activité</th>
                        <th scope=\"col\">Date</th>
                        <th scope=\"col\">Heure</th>
                        <th scope=\"col\">Statut</th>
                        <th scope=\"col\">Destination</th>
                        <th scope=\"col\">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    ";
            // line 26
            $context['_parent'] = $context;
            $context['_seq'] = CoreExtension::ensureTraversable((isset($context["activites"]) || array_key_exists("activites", $context) ? $context["activites"] : (function () { throw new RuntimeError('Variable "activites" does not exist.', 26, $this->source); })()));
            foreach ($context['_seq'] as $context["_key"] => $context["activite"]) {
                // line 27
                yield "                        <tr>
                            <td>";
                // line 28
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "id", [], "any", false, false, false, 28), "html", null, true);
                yield "</td>
                            <td>";
                // line 29
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "nomActivite", [], "any", false, false, false, 29), "html", null, true);
                yield "</td>
                            <td>";
                // line 30
                yield ((CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "date", [], "any", false, false, false, 30)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Twig\Extension\CoreExtension']->formatDate(CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "date", [], "any", false, false, false, 30), "Y-m-d"), "html", null, true)) : ("N/A"));
                yield "</td>
                            <td>";
                // line 31
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "heure", [], "any", false, false, false, 31), "html", null, true);
                yield "</td>
                            <td>";
                // line 32
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "statut", [], "any", false, false, false, 32), "html", null, true);
                yield "</td>
                            <td>";
                // line 33
                yield ((CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "idDestination", [], "any", false, false, false, 33)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(("Destination #" . CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "idDestination", [], "any", false, false, false, 33)), "html", null, true)) : ("N/A"));
                yield "</td>
                            <td>
                                <a href=\"";
                // line 35
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("edit_activite", ["id" => CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "id", [], "any", false, false, false, 35)]), "html", null, true);
                yield "\" class=\"btn btn-success btn-sm\">Modifier</a>
                                <a href=\"";
                // line 36
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("delete_activite", ["id" => CoreExtension::getAttribute($this->env, $this->source, $context["activite"], "id", [], "any", false, false, false, 36)]), "html", null, true);
                yield "\" class=\"btn btn-warning btn-sm\">Supprimer</a>
                            </td>
                        </tr>
                    ";
            }
            $_parent = $context['_parent'];
            unset($context['_seq'], $context['_key'], $context['activite'], $context['_parent']);
            $context = array_intersect_key($context, $_parent) + $_parent;
            // line 40
            yield "                    </tbody>
                </table>
            ";
        } else {
            // line 43
            yield "                <p class=\"text-muted\">Aucune activité trouvée.</p>
            ";
        }
        // line 45
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
        return "activite/list.html.twig";
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
        return array (  183 => 45,  179 => 43,  174 => 40,  164 => 36,  160 => 35,  155 => 33,  151 => 32,  147 => 31,  143 => 30,  139 => 29,  135 => 28,  132 => 27,  128 => 26,  113 => 13,  111 => 12,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base-back.html.twig' %}

{% block title %}Liste des Activités{% endblock %}

{% block body %}
    <main id=\"main\" class=\"main\">
        <div class=\"container\">
            <h1 class=\"mb-4\">Liste des Activités</h1>
            <input type=\"text\" id=\"search-input\" placeholder=\"Search...\">
            <a href=\"{{ path('add_activite') }}\" class=\"btn btn-info btn-sm\">Ajouter</a>

            {% if activites|length > 0 %}
                <table class=\"table table-striped\">
                    <thead>
                    <tr>
                        <th scope=\"col\">Id</th>
                        <th scope=\"col\">Nom Activité</th>
                        <th scope=\"col\">Date</th>
                        <th scope=\"col\">Heure</th>
                        <th scope=\"col\">Statut</th>
                        <th scope=\"col\">Destination</th>
                        <th scope=\"col\">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {% for activite in activites %}
                        <tr>
                            <td>{{ activite.id }}</td>
                            <td>{{ activite.nomActivite }}</td>
                            <td>{{ activite.date ? activite.date|date('Y-m-d') : 'N/A' }}</td>
                            <td>{{ activite.heure }}</td>
                            <td>{{ activite.statut }}</td>
                            <td>{{ activite.idDestination ? 'Destination #' ~ activite.idDestination : 'N/A' }}</td>
                            <td>
                                <a href=\"{{ path('edit_activite', {'id': activite.id}) }}\" class=\"btn btn-success btn-sm\">Modifier</a>
                                <a href=\"{{ path('delete_activite', {'id': activite.id}) }}\" class=\"btn btn-warning btn-sm\">Supprimer</a>
                            </td>
                        </tr>
                    {% endfor %}
                    </tbody>
                </table>
            {% else %}
                <p class=\"text-muted\">Aucune activité trouvée.</p>
            {% endif %}
        </div>
    </main>
{% endblock %}
", "activite/list.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\activite\\list.html.twig");
    }
}
