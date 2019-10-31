<#import "../common.ftl" as c/>
<@c.page title="CMS / Все документы">

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
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/docs.png);"></div>
                <div class="my-list-group-header-title">Все документы</div>
                <div class="ml-auto my-list-group-header-btns">
                    <div class="btn-group btn-group-sm">
                        <a class="btn btn-outline-secondary" href="/product/create">Добавить</a>
                    </div>
                </div>
            </div>
        </li>
        <li class="list-group-item my-list-group-body">
            <table class="table table-bordered table-sm table-hover">
                <thead class="bg-light">
                <tr>
                    <th class="my-table-code">№</th>
                    <th>Дата</th>
                    <th>Статус</th>
                    <th>Тип</th>
                    <th>Клиент</th>
                    <th>Кол-во</th>
                    <th>Сумма</th>
                </tr>
                </thead>
                <tbody>
                <#list docs as doc>

                    <#if doc.status == true>
                        <#assign status = "<span class='text-success'>Открыт</span>">
                    <#else>
                        <#assign status = "<span class='text-danger'>Закрыт</span>">
                    </#if>

                    <#if doc.type == true>
                        <#assign type = "Онлайн">
                    <#else>
                        <#assign type = "Офлайн">
                    </#if>

                    <tr onClick="location.href='/doc/get/${doc.id}'">
                        <td class="my-table-code">${doc.number?string["00000"]}</td>
                        <td>${doc.date}</td>
                        <td>${status}</td>
                        <td>${type}</td>
                        <td>${doc.customer.person.getFullName()}</td>
                        <td>${doc.getAllAmount()}</td>
                        <td>${doc.getSum()?string["0.00"]} грн.</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>