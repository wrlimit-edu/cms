<#import "../common.ftl" as c/>
<@c.page title="CMS / Документ">

    <#if successMessage??>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${successMessage}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>

    <#if errorMessage??>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${errorMessage}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>

    <#if doc??>
        <#if doc.status == true>
            <#assign status = "<span class='text-success'>Открыт</span>">
        <#else>
            <#assign status = "<span class='text-danger'>Закрыт</span>">
        </#if>

        <#if doc.type == true>
            <#assign type = "<span class='text-success'>Онлайн</span>">
        <#else>
            <#assign type = "<span class='text-primary'>Офлайн</span>">
        </#if>

        <#if doc.customer??>
            <#assign customer = doc.customer.person.getFullName()>
            <#assign customerDiscountName = "Скидка клиента: " + doc.customer.customerDiscount.getLongName()>
            <#assign customerBtnName = "Изменить клиента">
        <#else>
            <#assign customer = "<span class='text-danger'>Не выбран</span>">
            <#assign customerDiscountName = "">
            <#assign customerBtnName = "Добавить клиента">
        </#if>

        <ul class="list-group shadow-sm rounded">
            <li class="list-group-item p-0">
                <div class="d-flex flex-row">
                    <div class="my-list-group-header-icon" style="background-image: url(/images/icons48/doc.png);"></div>
                    <div class="my-list-group-header-title">Документ № ${doc.number?string["00000"]}</div>
                    <div class="ml-auto my-list-group-header-btns">
                        <#if doc.status == true>
                            <a class="btn btn-sm btn-outline-secondary" href="/doc/products/${doc.id}">Добавить товар</a>
                            <a class="btn btn-sm btn-outline-secondary" href="/doc/customers/${doc.id}">${customerBtnName}</a>
                            <a class="btn btn-sm btn-outline-secondary" href="/doc/editType/${doc.id}">Изменить тип</a>
                            <a class="btn btn-sm btn-outline-danger" href="/doc/complete/${doc.id}">Закрыть документ</a>
                        </#if>
                    </div>
                </div>
            </li>
            <li class="list-group-item my-list-group-body">
                <table class="table table-borderless table-sm">
                    <tbody>
                        <tr>
                            <td><strong>Клиент:</strong> ${customer}</td>
                            <td class="text-right" width="15%"><strong>Дата:</strong> ${doc.date}</td>
                            <td class="text-right" width="15%"><strong>Тип:</strong> ${type}</td>
                            <td class="text-right" width="15%"><strong>Статус:</strong> ${status}</td>
                        </tr>
                    </tbody>
                </table>
            </li>
            <#if doc.products??>
                <li class="list-group-item my-list-group-body">
                    <table class="table table-bordered table-sm table-hover">
                        <thead class="bg-light">
                            <tr>
                                <th class="my-table-code">№</th>
                                <th>Название</th>
                                <th>Описание</th>
                                <th>Скидка</th>
                                <th>Цена</th>
                                <th>Количество</th>
                                <th>Сумма</th>
                                <#if doc.status == true>
                                    <th class="my-table-edit"></th>
                                    <th class="my-table-edit"></th>
                                    <th class="my-table-edit"></th>
                                </#if>
                            </tr>
                        </thead>
                        <tbody>
                            <#list doc.products as product>
                                <tr>
                                    <td class="my-table-code">${product.number?string["00000"]}</td>
                                    <td>${product.name}</td>
                                    <td>${product.description}</td>
                                    <td>${product.productDiscount.getLongName()}</td>
                                    <td>${product.getFullPriceString()}</td>
                                    <td>${product.amount}</td>
                                    <td>${product.getSum()?string["0.00"]} грн.</td>
                                    <#if doc.status == true>
                                        <td class="my-table-edit" style="background-image: url(/images/icons48/add.png);" onClick="location.href='/doc/productPlus/${doc.id}/${product.id}'"></td>
                                        <td class="my-table-edit" style="background-image: url(/images/icons48/subtract.png);" onClick="location.href='/doc/productMinus/${doc.id}/${product.id}'"></td>
                                        <td class="my-table-edit" style="background-image: url(/images/icons48/delete.png);" onClick="location.href='/doc/productDelete/${doc.id}/${product.id}'"></td>
                                    </#if>
                                </tr>
                            </#list>
                            <tr>
                                <td class="text-right" colspan="6"><strong>Сумма документа:</strong></td>
                                <td colspan="4"><strong>${doc.getProductsSum()?string["0.00"]} грн.</strong></td>
                            </tr>
                            <#if doc.getProductsAmount() gt 4>
                                <tr>
                                    <td class="text-right" colspan="6"><strong>Скидка оптовой продажи (30%):</strong></td>
                                    <td colspan="4"><strong>- ${doc.getWholesaleDiscount()?string["0.00"]} грн.</strong></td>
                                </tr>
                            </#if>
                            <#if doc.getCustomerDiscount() != 0>
                                <tr>
                                    <td class="text-right" colspan="6"><strong>${customerDiscountName}</strong></td>
                                    <td colspan="4"><strong>- ${doc.getCustomerDiscount()?string["0.00"]} грн.</strong></td>
                                </tr>
                            </#if>
                            <tr>
                                <td class="text-right" colspan="6"><strong>К оплате:</strong></td>
                                <td colspan="4"><strong>${doc.getSum()?string["0.00"]} грн.</strong></td>
                            </tr>
                        </tbody>
                    </table>
                </li>
            </#if>
        </ul>

    <#else>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Ошибка! Документ не найден!
        </div>
    </#if>

</@c.page>