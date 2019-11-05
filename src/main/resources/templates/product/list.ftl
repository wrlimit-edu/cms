<#import "../common.ftl" as c/>
<@c.page title="CMS / Товары">

    <ul class="list-group shadow-sm rounded">
        <li class="list-group-item p-0">
            <div class="d-flex flex-row">
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/products.png);"></div>
                <div class="my-list-group-header-title">Товары</div>
                <div class="ml-auto my-list-group-header-btns">
                    <a class="btn btn-sm btn-outline-secondary" href="/product/create">Добавить товар</a>
                </div>
                <div class="my-list-group-header-search">
                    <form name="searchForm" action="/product/search" method="post">
                        <div class="input-group" style="margin: 0">
                            <input type="search" name="search" class="form-control form-control-sm border-secondary text-secondary" autofocus>
                            <div class="input-group-append">
                                <input type="submit" value="Найти" class="btn btn-sm btn-outline-secondary"/>
                            </div>
                        </div>
                    </form>
                </div>
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
                        <th class="my-table-edit"></th>
                    </tr>
                </thead>
                <tbody>
                    <#if products??>
                        <#list products as product>
                            <tr>
                                <td class="my-table-code">${product.number?string["00000"]}</td>
                                <td>${product.name}</td>
                                <td>${product.description}</td>
                                <td>${product.getFullPriceString()}</td>
                                <td>${product.productDiscount.name} (${product.productDiscount.value}%)</td>
                                <td>${product.amount}</td>
                                <td class="my-table-edit" style="background-image: url(/images/icons48/edit.png);" onClick="location.href='/product/update/${product.id}'"></td>
                            </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>