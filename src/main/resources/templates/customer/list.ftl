<#import "../common.ftl" as c/>
<@c.page title="CMS / Клиенты">

    <#if successMessage??>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMessage}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/contacts.png);"></div>
                <div class="my-list-group-header-title">Клиенты</div>
                <div class="ml-auto my-list-group-header-btns">
                    <div class="form-row">
                        <div class="form-group col" style="margin-bottom: 0">
                            <a class="btn btn-sm btn-outline-secondary" href="/customer/create">Добавить клиента</a>
                        </div>
                    </div>
                </div>
            </div>
        </li>
        <li class="list-group-item my-list-group-body">
            <table class="table table-bordered table-sm table-hover">
                <thead class="bg-light">
                <tr>
                    <th class="my-table-code">№</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Пол</th>
                    <th>Телефон</th>
                    <th>Адрес</th>
                    <th>Скидка</th>
                    <th>Статус</th>
                    <th class="my-table-edit"></th>
                </tr>
                </thead>
                <tbody>

                <#list customers as customer>
                    <#if customer.person.gender == true>
                        <#assign gender = "Мужской">
                    <#else>
                        <#assign gender = "Женский">
                    </#if>

                    <#if customer.enabled == true>
                        <#assign enabled = "<span class='text-success'>Включен</span>">
                    <#else>
                        <#assign enabled = "<span class='text-danger'>Отключен</span>">
                    </#if>

                    <#assign number = customer.number>
                    <tr>
                        <td class="my-table-code">${number?string["00000"]}</td>
                        <td>${customer.person.lastName}</td>
                        <td>${customer.person.firstName}</td>
                        <td>${customer.person.middleName}</td>
                        <td>${gender}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.address}</td>
                        <td>${customer.customerDiscount.name} (${customer.customerDiscount.value}%)</td>
                        <td>${enabled}</td>
                        <td class="my-table-edit" style="background-image: url(/images/icons48/edit.png);" onClick="location.href='/customer/update/${customer.id}'"></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>