<#import "../common.ftl" as c/>
<@c.page title="CMS / Добавить клиента">

    <div class="row h-100 justify-content-center align-items-center">
        <form name="person"  action="/person/add"  method="post">
            <ul class="list-group shadow-sm rounded" style="width: 600px;">
                <li class="list-group-item p-0">
                    <div class="d-flex flex-row">
                        <div class="my-form-group-header-icon" style="background-image: url(/images/icons48/add.png);"></div>
                        <div class="my-form-group-header-title">Добавить клиента</div>
                    </div>
                </li>
                <li class="list-group-item my-form-group-body">

                    <#assign name = "">
                    <#--
                    <#if country??>
                        <#assign name = country.name>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            Ошибка! Страна <strong>${country.name}</strong> уже существует в базе данных!
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>
                    -->

                    <div class="form-group row">
                        <label for="lastName" class="col-sm-3 col-form-label">Фамилия</label>
                        <div class="col-sm-9">
                            <input name="lastName" id="lastName" class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="firstName" class="col-sm-3 col-form-label">Имя</label>
                        <div class="col-sm-9">
                            <input name="firstName" id="firstName" class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="middleName" class="col-sm-3 col-form-label">Отчество</label>
                        <div class="col-sm-9">
                            <input name="middleName" id="middleName" class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="phone" class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col-sm-9">
                            <input name="phone" id="phone" class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="address" class="col-sm-3 col-form-label">Адрес</label>
                        <div class="col-sm-9">
                            <input name="address" id="address" class="form-control" type="text">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="discount" class="col-sm-3 col-form-label">Скидка</label>
                        <div class="col-sm-9">
                            <select name="discount" id="discount" class="form-control">
                                <option value="0" selected>Нет скидки</option>
                                <option value="3">3%</option>
                                <option value="5">5%</option>
                                <option value="7">7%</option>
                                <option value="10">10%</option>
                                <option value="15">15%</option>
                                <option value="20">20%</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="gender" class="col-sm-3 col-form-label">Пол</label>
                        <div class="col-sm-9" style="padding-top: 6px;">
                            <div class="form-check form-check-inline" style="padding-right: 50px">
                                <input name="gender" id="inlineRadio1" class="form-check-input" type="radio" value="true" checked>
                                <label for="inlineRadio1" class="form-check-label">Мужской</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input name="gender" id="inlineRadio2" class="form-check-input" type="radio" value="false">
                                <label for="inlineRadio2" class="form-check-label">Женский</label>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="list-group-item text-right my-form-group-footer">
                    <input type="submit" value="Сохранить"  class="btn btn-sm btn-outline-secondary"/>
                </li>
            </ul>
        </form>
    </div>

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


    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/add.png);"></div>
                <div class="my-list-group-header-title">Добавить клиента</div>
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
    -->

</@c.page>
