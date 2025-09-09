<%--
  Created by IntelliJ IDEA.
  User: francesco.basile
  Date: 21/08/2025
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Home - MyApp</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        header {
            background-color: #dc3545; /* rosso chiaro */
            color: white;
            padding: 1rem;
        }
        footer {
            background-color: #6c757d; /* grigio */
            color: white;
            padding: 1rem;
            margin-top: 2rem;
        }
        .sidebar {
            background-color: #f1f1f1;
            min-height: 100vh;
            padding-top: 1rem;
        }
        .nav-link i {
            margin-right: 6px;
        }
    </style>
</head>
<body>

<!-- Banner/Logo -->
<header class="d-flex align-items-center justify-content-between">
    <h1 class="h3 m-0"><i class="bi bi-layers"></i> MyApp</h1>
    <span><%= "Hello World!" %></span>
</header>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><i class="bi bi-house"></i> Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav"
                aria-controls="mainNav" aria-expanded="false" aria-label="Attiva navigazione">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="hello-servlet"><i class="bi bi-box-arrow-in-right"></i> Hello Servlet</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-link"></i> Link 2</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-link-45deg"></i> Link 3</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Breadcrumb -->
<div class="container-fluid mt-2">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#"><i class="bi bi-house"></i> Home</a></li>
            <li class="breadcrumb-item active" aria-current="page"><i class="bi bi-speedometer2"></i> Dashboard</li>
        </ol>
    </nav>
</div>

<!-- Layout con Sidebar + Content -->
<div class="container-fluid">
    <div class="row">
        <!-- Left Menu Nav -->
        <aside class="col-md-3 col-lg-2 sidebar">
            <ul class="nav flex-column">
                <li class="nav-item"><a class="nav-link active" href="#"><i class="bi bi-speedometer2"></i> Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-gear"></i> Impostazioni</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-folder"></i> Sezione 1</a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="bi bi-folder2"></i> Sezione 2</a></li>
            </ul>
        </aside>

        <!-- Main Content Area -->
        <main class="col-md-9 col-lg-10 px-4 py-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="card-title"><i class="bi bi-person-circle"></i> Benvenuto!</h2>
                    <p class="card-text">
                        Questa è l’area contenuti principale della tua applicazione.
                        Qui puoi usare variabili JSP, ad esempio:
                    </p>
                    <div class="alert alert-secondary">
                        Variabile JSP: <strong><%= "Hello World!" %></strong>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- Footer -->
<footer class="text-center">
    <p class="mb-0">&copy; <%= java.time.Year.now() %> MyApp - Tutti i diritti riservati</p>
</footer>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

