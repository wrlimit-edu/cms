<#import "../common.ftl" as c/>
<@c.page title="CMS / Товары">

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
                <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/box.png);"></div>
                <div class="my-list-group-header-title">Товары</div>
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
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Цена</th>
                        <th>Скидка</th>
                        <th>Количество</th>
                        <th class="my-table-edit"></th>
                    </tr>
                </thead>
                <tbody>
                    <#list products as product>
                        <#--
                        <#if product.productDiscount.value == 0>
                            <#assign price = product.price?string["0.00"] + " грн.">
                        <#else>
                            <#assign price = "<s>" + product.price?string["0.00"] + " грн.</s> <span class='text-danger'>" + product.getDiscountPrice()?string["0.00"] + ' грн.</span>'>
                        </#if>
                        -->

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
                </tbody>
            </table>
        </li>
    </ul>

</@c.page>