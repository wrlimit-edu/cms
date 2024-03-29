<#import "../common.ftl" as c/>
<@c.page title="CMS / Скидки товаров">

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/discount.png);"></div>
                <div class="my-list-group-header-title">Скидки товаров</div>
                <div class="ml-auto my-list-group-header-btns">
                    <div class="btn-group btn-group-sm">
                        <a class="btn btn-outline-secondary" href="/productDiscount/create">Добавить</a>
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
                        <th>Статус</th>
                        <th class="my-table-edit"></th>
                    </tr>
                </thead>
                <tbody>
                    <#list productDiscounts as productDiscount>
                        <#if productDiscount.enabled == true>
                            <#assign enabled = "<span class='text-success'>Включен</span>">
                        <#else>
                            <#assign enabled = "<span class='text-danger'>Отключен</span>">
                        </#if>
                        <tr>
                            <td>${productDiscount.name}</td>
                            <td>Скидка ${productDiscount.value}%</td>
                            <td>${enabled}</td>
                            <td class="my-table-edit" style="background-image: url(/images/icons48/edit.png);" onClick="location.href='/productDiscount/update/${productDiscount.id}'"></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>