<#import "../common.ftl" as c/>
<@c.page title="CMS / Все клиенты">

    <#--
    <#if country??>
        <#assign name = country.name>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            Страна <strong>${country.name}</strong> добавлена в базу данных!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>
    -->

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/contacts.png);"></div>
                <div class="my-list-group-header-title">Все клиенты</div>
                <div class="ml-auto my-list-group-header-btns">
                    <div class="btn-group btn-group-sm">
                        <a class="btn btn-outline-secondary" href="/person/search">Поиск</a>
                        <a class="btn btn-outline-secondary" href="/person/add">Добавить</a>
                    </div>
                </div>
            </div>
        </li>
        <li class="list-group-item my-list-group-body">
            <table class="table table-bordered table-sm table-hover">
                <thead class="bg-light">
                <tr>
                    <th class="my-table-code">Код</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Пол</th>
                    <th>Телефон</th>
                    <th>Адрес</th>
                    <th>Скидка</th>
                </tr>
                </thead>
                <tbody>

                <#assign gender = "">
                <#list persons as person>
                    <#if person.gender == true>
                        <#assign gender = "Мужской">
                    <#else>
                        <#assign gender = "Женский">
                    </#if>
                    <tr data-toggle="collapse" href="#collapse${person.id}">
                        <td class="my-table-code">${person.number}</td>
                        <td>${person.lastName}</td>
                        <td>${person.firstName}</td>
                        <td>${person.middleName}</td>
                        <td>${gender}</td>
                        <td>${person.phone}</td>
                        <td>${person.address}</td>
                        <td>${person.discount} %</td>
                    </tr>
                    <tr class="collapse" id="collapse${person.id}">
                        <td colspan="3">
                            <div class="btn-group btn-group-sm" role="group">
                                <a class="btn btn-outline-secondary" href="/country/edit/${person.id}">Редактировать</a>
                                <a class="btn btn-outline-secondary" href="/country/delete/${person.id}">Удалить</a>
                            </div>
                        </td>
                    </tr>
                </#list>

                </tbody>
            </table>
        </li>
    </ul>

</@c.page>
