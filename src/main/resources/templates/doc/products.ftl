<#import "../common.ftl" as c/>
<@c.page title="CMS / Выбрать товар">

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/box.png);"></div>
                <div class="my-list-group-header-title">Выбрать товар</div>
            </div>
        </li>
        <li class="list-group-item my-list-group-body">
            <table class="table table-bordered table-sm table-hover">
                <thead class="bg-light">
                    <tr>
                        <th class="my-table-code">№</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Цена</th>
                        <th>Скидка</th>
                        <th>Количество</th>
                    </tr>
                </thead>
                <tbody>
                    <#list products as product>
                        <tr style="cursor: pointer" onClick="location.href='/doc/productAdd/${doc.id}/${product.id}'">
                            <td class="my-table-code">${product.number?string["00000"]}</td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.getFullPriceString()}</td>
                            <td>${product.productDiscount.getLongName()}</td>
                            <td>${product.amount}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>