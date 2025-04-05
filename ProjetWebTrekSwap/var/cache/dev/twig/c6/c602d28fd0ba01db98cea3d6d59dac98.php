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

/* pack/showPack.html.twig */
class __TwigTemplate_050461de7c1de1a6ee3af960c245acbf extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "pack/showPack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "pack/showPack.html.twig"));

        $this->parent = $this->loadTemplate("base-back.html.twig", "pack/showPack.html.twig", 1);
        yield from $this->parent->unwrap()->yield($context, array_merge($this->blocks, $blocks));
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

    }

    // line 3
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

        // line 4
        yield "<main id=\"main\" class=\"main\">
    <div class=\"container mt-5\">
        <h1 class=\"text-center mb-4 text-warning\">Liste des Packs</h1>
        
        <table class=\"table table-bordered\">
            <thead class=\"table-dark\">
                <tr>
                    <th>ID</th>
                    <th>Nom Pack</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Durée</th>
                    <th>Avantages</th>
                    <th>Statut</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                ";
        // line 22
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["packs"]) || array_key_exists("packs", $context) ? $context["packs"] : (function () { throw new RuntimeError('Variable "packs" does not exist.', 22, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["pack"]) {
            // line 23
            yield "                    <tr>
                        <td>";
            // line 24
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getIdPack", [], "method", false, false, false, 24), "html", null, true);
            yield "</td>  ";
            // line 25
            yield "                        <td>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getNomPack", [], "method", false, false, false, 25), "html", null, true);
            yield "</td>  ";
            // line 26
            yield "                        <td>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getDescription", [], "method", false, false, false, 26), "html", null, true);
            yield "</td>  ";
            // line 27
            yield "                        <td>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getPrix", [], "method", false, false, false, 27), "html", null, true);
            yield " dt</td>  ";
            // line 28
            yield "                        <td>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getDuree", [], "method", false, false, false, 28), "html", null, true);
            yield "</td>  ";
            // line 29
            yield "                        <td>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getAvantages", [], "method", false, false, false, 29), "html", null, true);
            yield "</td>  ";
            // line 30
            yield "                        <td>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getStatut", [], "method", false, false, false, 30), "html", null, true);
            yield "</td>  ";
            // line 31
            yield "                        <td>
                            <a href=\"";
            // line 32
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("edit_pack", ["id_pack" => CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getIdPack", [], "method", false, false, false, 32)]), "html", null, true);
            yield "\" class=\"btn btn-info btn-sm\">
                               Modifier 
                            </a>
                             <a href=\"";
            // line 35
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("delete_pack", ["id_pack" => CoreExtension::getAttribute($this->env, $this->source, $context["pack"], "getIdPack", [], "method", false, false, false, 35)]), "html", null, true);
            yield "\" class=\"btn btn-info btn-sm\">
                              Supprimer
                            </a>
                        </td>
                         
                    </tr>
                ";
            $context['_iterated'] = true;
        }
        // line 41
        if (!$context['_iterated']) {
            // line 42
            yield "                    <tr>
                        <td colspan=\"8\" class=\"text-center\">Aucun pack trouvé.</td>
                    </tr>
                ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['pack'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 46
        yield "            </tbody>
        </table>
        
        <div class=\"text-center mt-4\">
            <a href=\"";
        // line 50
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("add_pack");
        yield "\" class=\"btn btn-success\">Ajouter un Pack</a>
        </div>
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
        return "pack/showPack.html.twig";
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
        return array (  168 => 50,  162 => 46,  153 => 42,  151 => 41,  140 => 35,  134 => 32,  131 => 31,  127 => 30,  123 => 29,  119 => 28,  115 => 27,  111 => 26,  107 => 25,  104 => 24,  101 => 23,  96 => 22,  76 => 4,  63 => 3,  40 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base-back.html.twig' %}

{% block body %}
<main id=\"main\" class=\"main\">
    <div class=\"container mt-5\">
        <h1 class=\"text-center mb-4 text-warning\">Liste des Packs</h1>
        
        <table class=\"table table-bordered\">
            <thead class=\"table-dark\">
                <tr>
                    <th>ID</th>
                    <th>Nom Pack</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Durée</th>
                    <th>Avantages</th>
                    <th>Statut</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {% for pack in packs %}
                    <tr>
                        <td>{{ pack.getIdPack() }}</td>  {# Access ID using getter method #}
                        <td>{{ pack.getNomPack() }}</td>  {# Access nom_pack using getter method #}
                        <td>{{ pack.getDescription() }}</td>  {# Access description using getter method #}
                        <td>{{ pack.getPrix() }} dt</td>  {# Access prix using getter method #}
                        <td>{{ pack.getDuree() }}</td>  {# Access duree using getter method #}
                        <td>{{ pack.getAvantages() }}</td>  {# Access avantages using getter method #}
                        <td>{{ pack.getStatut() }}</td>  {# Access statut using getter method #}
                        <td>
                            <a href=\"{{ path('edit_pack', { id_pack: pack.getIdPack() }) }}\" class=\"btn btn-info btn-sm\">
                               Modifier 
                            </a>
                             <a href=\"{{ path('delete_pack', { id_pack: pack.getIdPack() }) }}\" class=\"btn btn-info btn-sm\">
                              Supprimer
                            </a>
                        </td>
                         
                    </tr>
                {% else %}
                    <tr>
                        <td colspan=\"8\" class=\"text-center\">Aucun pack trouvé.</td>
                    </tr>
                {% endfor %}
            </tbody>
        </table>
        
        <div class=\"text-center mt-4\">
            <a href=\"{{ path('add_pack') }}\" class=\"btn btn-success\">Ajouter un Pack</a>
        </div>
    </div>
</main>
{% endblock %}
", "pack/showPack.html.twig", "C:\\Users\\ahmed\\OneDrive\\Desktop\\pi web\\PI2025\\ProjetWebTrekSwap\\templates\\pack\\showPack.html.twig");
    }
}
