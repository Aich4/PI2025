<?php

/**
 * This file has been auto-generated
 * by the Symfony Routing Component.
 */

return [
    false, // $matchHost
    [ // $staticRoutes
        '/_profiler' => [[['_route' => '_profiler_home', '_controller' => 'web_profiler.controller.profiler::homeAction'], null, null, null, true, false, null]],
        '/_profiler/search' => [[['_route' => '_profiler_search', '_controller' => 'web_profiler.controller.profiler::searchAction'], null, null, null, false, false, null]],
        '/_profiler/search_bar' => [[['_route' => '_profiler_search_bar', '_controller' => 'web_profiler.controller.profiler::searchBarAction'], null, null, null, false, false, null]],
        '/_profiler/phpinfo' => [[['_route' => '_profiler_phpinfo', '_controller' => 'web_profiler.controller.profiler::phpinfoAction'], null, null, null, false, false, null]],
        '/_profiler/xdebug' => [[['_route' => '_profiler_xdebug', '_controller' => 'web_profiler.controller.profiler::xdebugAction'], null, null, null, false, false, null]],
        '/_profiler/open' => [[['_route' => '_profiler_open_file', '_controller' => 'web_profiler.controller.profiler::openAction'], null, null, null, false, false, null]],
        '/' => [[['_route' => 'homepage', '_controller' => 'App\\Controller\\AbonnementController::home'], null, null, null, false, false, null]],
        '/frontA' => [[['_route' => 'app_Abonnement', '_controller' => 'App\\Controller\\AbonnementController::indexA'], null, null, null, false, false, null]],
        '/backA' => [[['_route' => 'app_Abonnements', '_controller' => 'App\\Controller\\AbonnementController::indexBA'], null, null, null, false, false, null]],
        '/Abonshow' => [[['_route' => 'list_Abonnement', '_controller' => 'App\\Controller\\AbonnementController::listAbonnements'], null, ['GET' => 0], null, false, false, null]],
        '/Abonadd' => [[['_route' => 'add_Abonnement', '_controller' => 'App\\Controller\\AbonnementController::add'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/activite' => [[['_route' => 'app_activite', '_controller' => 'App\\Controller\\ActiviteController::index'], null, null, null, false, false, null]],
        '/activite/show' => [[['_route' => 'list_activite', '_controller' => 'App\\Controller\\ActiviteController::list'], null, ['GET' => 0], null, false, false, null]],
        '/activite/add' => [[['_route' => 'add_activite', '_controller' => 'App\\Controller\\ActiviteController::add'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/admin' => [[['_route' => 'app_admin', '_controller' => 'App\\Controller\\AdminController::index'], null, null, null, false, false, null]],
        '/categorie' => [[['_route' => 'app_categorie', '_controller' => 'App\\Controller\\CategorieController::index'], null, null, null, false, false, null]],
        '/Catshow' => [[['_route' => 'list_category', '_controller' => 'App\\Controller\\CategorieController::listCategories'], null, ['GET' => 0], null, false, false, null]],
        '/categorie/add' => [[['_route' => 'add_category', '_controller' => 'App\\Controller\\CategorieController::addCategory'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/destination' => [[['_route' => 'app_destination', '_controller' => 'App\\Controller\\DestinationController::index'], null, null, null, false, false, null]],
        '/DestinationBackShow' => [[['_route' => 'list_destination', '_controller' => 'App\\Controller\\DestinationController::list_Abonnement'], null, ['GET' => 0], null, false, false, null]],
        '/DestinationFrontShow' => [[['_route' => 'listFrontDestination', '_controller' => 'App\\Controller\\DestinationController::listDestination'], null, ['GET' => 0], null, false, false, null]],
        '/addDestination' => [[['_route' => 'addDestination', '_controller' => 'App\\Controller\\DestinationController::add'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/front' => [[['_route' => 'app_pack', '_controller' => 'App\\Controller\\PackController::index'], null, null, null, false, false, null]],
        '/back' => [[['_route' => 'app_packk', '_controller' => 'App\\Controller\\PackController::indexB'], null, null, null, false, false, null]],
        '/packshow' => [[['_route' => 'list_packs', '_controller' => 'App\\Controller\\PackController::listPacks'], null, ['GET' => 0], null, false, false, null]],
        '/packadd' => [[['_route' => 'add_pack', '_controller' => 'App\\Controller\\PackController::add'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/partenaire' => [[['_route' => 'app_partenaire', '_controller' => 'App\\Controller\\PartenaireController::index'], null, null, null, false, false, null]],
        '/Partenaires' => [[['_route' => 'list_partenaire', '_controller' => 'App\\Controller\\PartenaireController::listPartenaires'], null, ['GET' => 0], null, false, false, null]],
        '/partenaire/add' => [[['_route' => 'add_partenaire', '_controller' => 'App\\Controller\\PartenaireController::addPartenaire'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/profile' => [[['_route' => 'app_profile', '_controller' => 'App\\Controller\\ProfileController::edit'], null, null, null, false, false, null]],
        '/profile/delete' => [[['_route' => 'app_profile_delete', '_controller' => 'App\\Controller\\ProfileController::delete'], null, ['POST' => 0], null, false, false, null]],
        '/reclamation' => [[['_route' => 'app_reclamation_index', '_controller' => 'App\\Controller\\ReclamationController::index'], null, ['GET' => 0], null, false, false, null]],
        '/reclamation/back' => [[['_route' => 'reclamationBack', '_controller' => 'App\\Controller\\ReclamationController::showback'], null, ['GET' => 0], null, false, false, null]],
        '/reclamation/new' => [[['_route' => 'app_reclamation_new', '_controller' => 'App\\Controller\\ReclamationController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/reponse' => [[['_route' => 'app_reponse_index', '_controller' => 'App\\Controller\\ReponseController::index'], null, ['GET' => 0], null, false, false, null]],
        '/reponse/new' => [[['_route' => 'app_reponse_new', '_controller' => 'App\\Controller\\ReponseController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/login' => [[['_route' => 'app_login', '_controller' => 'App\\Controller\\SecurityController::login'], null, null, null, false, false, null]],
        '/register' => [[['_route' => 'app_register', '_controller' => 'App\\Controller\\SecurityController::register'], null, null, null, false, false, null]],
        '/logout' => [[['_route' => 'app_logout', '_controller' => 'App\\Controller\\SecurityController::logout'], null, null, null, false, false, null]],
    ],
    [ // $regexpList
        0 => '{^(?'
                .'|/_(?'
                    .'|error/(\\d+)(?:\\.([^/]++))?(*:38)'
                    .'|wdt/([^/]++)(*:57)'
                    .'|profiler/(?'
                        .'|font/([^/\\.]++)\\.woff2(*:98)'
                        .'|([^/]++)(?'
                            .'|/(?'
                                .'|search/results(*:134)'
                                .'|router(*:148)'
                                .'|exception(?'
                                    .'|(*:168)'
                                    .'|\\.css(*:181)'
                                .')'
                            .')'
                            .'|(*:191)'
                        .')'
                    .')'
                .')'
                .'|/Abon(?'
                    .'|edit/([^/]++)(*:223)'
                    .'|delete/([^/]++)(*:246)'
                .')'
                .'|/activite/(?'
                    .'|edit/([^/]++)(*:281)'
                    .'|delete/([^/]++)(*:304)'
                .')'
                .'|/Categorie/(?'
                    .'|edit/([^/]++)(*:340)'
                    .'|delete/([^/]++)(*:363)'
                .')'
                .'|/editDestination/([^/]++)(*:397)'
                .'|/deleteDestination/([^/]++)(*:432)'
                .'|/pa(?'
                    .'|ck(?'
                        .'|edit/([^/]++)(*:464)'
                        .'|delete/([^/]++)(*:487)'
                    .')'
                    .'|rtenaire/(?'
                        .'|edit/([^/]++)(*:521)'
                        .'|delete/([^/]++)(*:544)'
                    .')'
                .')'
                .'|/re(?'
                    .'|clamation/([^/]++)(?'
                        .'|(*:581)'
                        .'|/edit(*:594)'
                        .'|(*:602)'
                    .')'
                    .'|ponse/([^/]++)(?'
                        .'|(*:628)'
                        .'|/edit(*:641)'
                        .'|(*:649)'
                    .')'
                .')'
            .')/?$}sDu',
    ],
    [ // $dynamicRoutes
        38 => [[['_route' => '_preview_error', '_controller' => 'error_controller::preview', '_format' => 'html'], ['code', '_format'], null, null, false, true, null]],
        57 => [[['_route' => '_wdt', '_controller' => 'web_profiler.controller.profiler::toolbarAction'], ['token'], null, null, false, true, null]],
        98 => [[['_route' => '_profiler_font', '_controller' => 'web_profiler.controller.profiler::fontAction'], ['fontName'], null, null, false, false, null]],
        134 => [[['_route' => '_profiler_search_results', '_controller' => 'web_profiler.controller.profiler::searchResultsAction'], ['token'], null, null, false, false, null]],
        148 => [[['_route' => '_profiler_router', '_controller' => 'web_profiler.controller.router::panelAction'], ['token'], null, null, false, false, null]],
        168 => [[['_route' => '_profiler_exception', '_controller' => 'web_profiler.controller.exception_panel::body'], ['token'], null, null, false, false, null]],
        181 => [[['_route' => '_profiler_exception_css', '_controller' => 'web_profiler.controller.exception_panel::stylesheet'], ['token'], null, null, false, false, null]],
        191 => [[['_route' => '_profiler', '_controller' => 'web_profiler.controller.profiler::panelAction'], ['token'], null, null, false, true, null]],
        223 => [[['_route' => 'edit_Abonnement', '_controller' => 'App\\Controller\\AbonnementController::edit'], ['id_abonnement'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        246 => [[['_route' => 'delete_Abonnement', '_controller' => 'App\\Controller\\AbonnementController::delete'], ['id_abonnement'], ['GET' => 0], null, false, true, null]],
        281 => [[['_route' => 'edit_activite', '_controller' => 'App\\Controller\\ActiviteController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        304 => [[['_route' => 'delete_activite', '_controller' => 'App\\Controller\\ActiviteController::delete'], ['id'], ['GET' => 0], null, false, true, null]],
        340 => [[['_route' => 'edit_category', '_controller' => 'App\\Controller\\CategorieController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        363 => [[['_route' => 'delete_category', '_controller' => 'App\\Controller\\CategorieController::delete'], ['id'], ['GET' => 0], null, false, true, null]],
        397 => [[['_route' => 'editDest', '_controller' => 'App\\Controller\\DestinationController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        432 => [[['_route' => 'deleteDest', '_controller' => 'App\\Controller\\DestinationController::delete'], ['id'], ['GET' => 0], null, false, true, null]],
        464 => [[['_route' => 'edit_pack', '_controller' => 'App\\Controller\\PackController::edit'], ['id_pack'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        487 => [[['_route' => 'delete_pack', '_controller' => 'App\\Controller\\PackController::delete'], ['id_pack'], ['GET' => 0], null, false, true, null]],
        521 => [[['_route' => 'edit_partenaire', '_controller' => 'App\\Controller\\PartenaireController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        544 => [[['_route' => 'delete_partenaire', '_controller' => 'App\\Controller\\PartenaireController::delete'], ['id'], ['GET' => 0], null, false, true, null]],
        581 => [[['_route' => 'app_reclamation_show', '_controller' => 'App\\Controller\\ReclamationController::show'], ['id_rec'], ['GET' => 0], null, false, true, null]],
        594 => [[['_route' => 'app_reclamation_edit', '_controller' => 'App\\Controller\\ReclamationController::edit'], ['id_rec'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        602 => [[['_route' => 'app_reclamation_delete', '_controller' => 'App\\Controller\\ReclamationController::delete'], ['id_rec'], ['POST' => 0], null, false, true, null]],
        628 => [[['_route' => 'app_reponse_show', '_controller' => 'App\\Controller\\ReponseController::show'], ['id_rep'], ['GET' => 0], null, false, true, null]],
        641 => [[['_route' => 'app_reponse_edit', '_controller' => 'App\\Controller\\ReponseController::edit'], ['id_rep'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        649 => [
            [['_route' => 'app_reponse_delete', '_controller' => 'App\\Controller\\ReponseController::delete'], ['id_rep'], ['POST' => 0], null, false, true, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
