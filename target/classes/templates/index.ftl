<#import "common.ftl" as c/>
<@c.page title="CMS Index">

    <div class="row h-100 justify-content-center align-items-center">
        <ul class="list-group">
            <li class="list-group-item border-0 my-pg-btn-line">
                <button type="button" class="btn btn-outline-secondary btn-lg my-pg-btn"
                        style="background-image: url(/images/icons48/cart.png);"
                        onClick="location.href='/doc/create'">
                    <div class="my-pg-btn-name">Новый документ</div>
                </button>
                <button type="button" class="btn btn-outline-secondary btn-lg my-pg-btn"
                        style="background-image: url(/images/icons48/contacts.png);"
                        onClick="location.href='/customer/list'">
                    <div class="my-pg-btn-name">Все клиенты</div>
                </button>
                <button type="button" class="btn btn-outline-secondary btn-lg my-pg-btn"
                        style="background-image: url(/images/icons48/docs.png);"
                        onClick="location.href='/doc/list'">
                    <div class="my-pg-btn-name">Все документы</div>
                </button>
                <button type="button" class="btn btn-outline-secondary btn-lg my-pg-btn"
                        style="background-image: url(/images/icons48/box.png);"
                        onClick="location.href='/product/list'">
                    <div class="my-pg-btn-name">Все товары</div>
                </button>
            </li>
        </ul>
    </div>

</@c.page>
