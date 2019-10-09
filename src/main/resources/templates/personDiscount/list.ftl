<#import "../common.ftl" as c/>
<@c.page title="CMS / Скидки для клиентов">

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/discount.png);"></div>
                <div class="my-list-group-header-title">Скидки для клиентов</div>
                <div class="ml-auto my-list-group-header-btns">
                    <div class="btn-group btn-group-sm">
                        <a class="btn btn-outline-secondary" href="/personDiscount/add">Добавить</a>
                    </div>
                </div>
            </div>
        </li>
        <li class="list-group-item my-list-group-body">
            <table class="table table-bordered table-sm table-hover">
                <thead class="bg-light">
                <tr>
                    <th>Название</th>
                    <th>Скидка</th>
                </tr>
                </thead>
                <tbody>
                <#list personDiscounts as personDiscount>
                    <tr>
                        <td>${personDiscount.name}</td>
                        <td>${personDiscount.value}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>
