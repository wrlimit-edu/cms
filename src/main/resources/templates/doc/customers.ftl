<#import "../common.ftl" as c/>
<@c.page title="CMS / Выбрать клиента">

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/contacts.png);"></div>
                <div class="my-list-group-header-title">Выбрать клиента</div>
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
                        <tr style="cursor: pointer" onClick="location.href='/doc/customerAdd/${doc.id}/${customer.id}'">
                            <td class="my-table-code">${number?string["00000"]}</td>
                            <td>${customer.person.lastName}</td>
                            <td>${customer.person.firstName}</td>
                            <td>${customer.person.middleName}</td>
                            <td>${gender}</td>
                            <td>${customer.phone}</td>
                            <td>${customer.address}</td>
                            <td>${customer.customerDiscount.getLongName()}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>