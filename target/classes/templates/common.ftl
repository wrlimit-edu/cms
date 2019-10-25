<#macro page title>
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>${title}</title>
            <link rel="stylesheet"
                  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
                  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                  crossorigin="anonymous">
            <link rel="stylesheet" type="text/css" href="/css/style.css">
            <link rel="icon" href="/images/logo.png" type="image/png">
        </head>
        <body>
            <nav class="navbar navbar-expand-sm navbar-dark bg-dark sticky-top">
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link btn-sm text-success nav-logo" href="/">
                                <img src="/images/logo.png" width="20" height="20">
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link btn-sm" id="navbarPersons" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Клиенты
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarPersons">
                                <a class="dropdown-item btn-sm" href="/customer/list">Все клиенты</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item btn-sm" href="/customer/search">Поиск</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item btn-sm" href="/customer/create">Добавить</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link btn-sm" id="navbarDocs" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Документы
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDocs">
                                <a class="dropdown-item btn-sm" href="/doc/list">Все документы</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item btn-sm" href="/doc/create">Добавить</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link btn-sm" id="navbarProducts" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Товары
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarProducts">
                                <a class="dropdown-item btn-sm" href="/product/list">Все товары</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item btn-sm" href="/product/search">Поиск</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item btn-sm" href="/product/create">Добавить</a>
                            </div>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link btn-sm" id="navbarSettings" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Настройки
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarSettings">
                                <a class="dropdown-item btn-sm" href="/customerDiscount/list">Скидки для клиентов</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item btn-sm" href="/productDiscount/list">Скидки для товаров</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">
                <#nested/>
            </div>
            <!-- footer section -->

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        </body>
    </html>
</#macro>