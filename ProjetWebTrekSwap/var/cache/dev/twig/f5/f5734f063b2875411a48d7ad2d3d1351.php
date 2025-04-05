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

/* reclamation/back.html.twig */
class __TwigTemplate_47a408e8a22c5bdc8c133e0298fca0f8 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "reclamation/back.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "reclamation/back.html.twig"));

        $this->parent = $this->loadTemplate("base-back.html.twig", "reclamation/back.html.twig", 1);
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

        yield "listReclamationBack";
        
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
            <h1 class=\"mb-4\">Liste des réclamations</h1>
            <input type=\"text\" id=\"search-input\" placeholder=\"Search...\">
            <a href=\"";
        // line 10
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_reclamation_new");
        yield "\" class=\"btn btn-info btn-sm\">Ajouter</a>
            
            ";
        // line 12
        if ((Twig\Extension\CoreExtension::length($this->env->getCharset(), (isset($context["reclamations"]) || array_key_exists("reclamations", $context) ? $context["reclamations"] : (function () { throw new RuntimeError('Variable "reclamations" does not exist.', 12, $this->source); })())) > 0)) {
            // line 13
            yield "                <table class=\"table table-striped\">
                    <thead>
                        <tr>
                            <th scope=\"col\">Id</th>
                            <th scope=\"col\">Description</th>
                            <th scope=\"col\">Date</th>
                            <th scope=\"col\">Type</th>
                            <th scope=\"col\">État</th>
                            <th scope=\"col\">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        ";
            // line 25
            $context['_parent'] = $context;
            $context['_seq'] = CoreExtension::ensureTraversable((isset($context["reclamations"]) || array_key_exists("reclamations", $context) ? $context["reclamations"] : (function () { throw new RuntimeError('Variable "reclamations" does not exist.', 25, $this->source); })()));
            foreach ($context['_seq'] as $context["_key"] => $context["reclamation"]) {
                // line 26
                yield "                            <tr>
                                <td>";
                // line 27
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "idRec", [], "any", false, false, false, 27), "html", null, true);
                yield "</td>
                                <td>";
                // line 28
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "descriptionRec", [], "any", false, false, false, 28), "html", null, true);
                yield "</td>
                                <td>";
                // line 29
                yield ((CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "dateRec", [], "any", false, false, false, 29)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Twig\Extension\CoreExtension']->formatDate(CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "dateRec", [], "any", false, false, false, 29), "Y-m-d H:i:s"), "html", null, true)) : (""));
                yield "</td>
                                <td>";
                // line 30
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "typeRec", [], "any", false, false, false, 30), "html", null, true);
                yield "</td>
                                <td>";
                // line 31
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "etatRec", [], "any", false, false, false, 31), "html", null, true);
                yield "</td>
                                <td>
                                    <a href=\"";
                // line 33
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_reclamation_edit", ["id_rec" => CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "idRec", [], "any", false, false, false, 33)]), "html", null, true);
                yield "\" class=\"btn btn-success btn-sm\">Modifier</a>
                                    <a href=\"";
                // line 34
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_reclamation_show", ["id_rec" => CoreExtension::getAttribute($this->env, $this->source, $context["reclamation"], "idRec", [], "any", false, false, false, 34)]), "html", null, true);
                yield "\" class=\"btn btn-primary btn-sm\">Afficher</a>
                                </td>
                            </tr>
                        ";
            }
            $_parent = $context['_parent'];
            unset($context['_seq'], $context['_key'], $context['reclamation'], $context['_parent']);
            $context = array_intersect_key($context, $_parent) + $_parent;
            // line 38
            yield "                    </tbody>
                </table>
            ";
        } else {
            // line 41
            yield "                <p class=\"text-muted\">Aucune réclamation trouvée.</p>
            ";
        }
        // line 43
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
        return "reclamation/back.html.twig";
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
        return array (  178 => 43,  174 => 41,  169 => 38,  159 => 34,  155 => 33,  150 => 31,  146 => 30,  142 => 29,  138 => 28,  134 => 27,  131 => 26,  127 => 25,  113 => 13,  111 => 12,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base-back.html.twig' %}

{% block title %}listReclamationBack{% endblock %}

{% block body %}
    <main id=\"main\" class=\"main\">
        <div class=\"container\">
            <h1 class=\"mb-4\">Liste des réclamations</h1>
            <input type=\"text\" id=\"search-input\" placeholder=\"Search...\">
            <a href=\"{{ path('app_reclamation_new') }}\" class=\"btn btn-info btn-sm\">Ajouter</a>
            
            {% if reclamations|length > 0 %}
                <table class=\"table table-striped\">
                    <thead>
                        <tr>
                            <th scope=\"col\">Id</th>
                            <th scope=\"col\">Description</th>
                            <th scope=\"col\">Date</th>
                            <th scope=\"col\">Type</th>
                            <th scope=\"col\">État</th>
                            <th scope=\"col\">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {% for reclamation in reclamations %}
                            <tr>
                                <td>{{ reclamation.idRec }}</td>
                                <td>{{ reclamation.descriptionRec }}</td>
                                <td>{{ reclamation.dateRec ? reclamation.dateRec|date('Y-m-d H:i:s') : '' }}</td>
                                <td>{{ reclamation.typeRec }}</td>
                                <td>{{ reclamation.etatRec }}</td>
                                <td>
                                    <a href=\"{{ path('app_reclamation_edit', {'id_rec': reclamation.idRec}) }}\" class=\"btn btn-success btn-sm\">Modifier</a>
                                    <a href=\"{{ path('app_reclamation_show', {'id_rec': reclamation.idRec}) }}\" class=\"btn btn-primary btn-sm\">Afficher</a>
                                </td>
                            </tr>
                        {% endfor %}
                    </tbody>
                </table>
            {% else %}
                <p class=\"text-muted\">Aucune réclamation trouvée.</p>
            {% endif %}
        </div>
    </main>
{% endblock %}
", "reclamation/back.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\reclamation\\back.html.twig");
    }
}
