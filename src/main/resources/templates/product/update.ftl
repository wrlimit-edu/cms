<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as c/>
<@c.page title="CMS / Редактировать товар">

    <div class="row h-100 justify-content-center align-items-center">
        <form name="person" action="/product/update" method="post">
            <ul class="list-group shadow-sm rounded" style="width: 600px;">
                <li class="list-group-item p-0">
                    <div class="d-flex flex-row">
                        <div class="my-form-group-header-icon" style="background-image: url(/images/icons48/edit.png);"></div>
                        <div class="my-form-group-header-title">Редактировать товар</div>
                    </div>
                </li>
                <li class="list-group-item my-form-group-body">

                    <#if errorMessage??>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${errorMessage}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </#if>

                    <@spring.formHiddenInput "productForm.id"/>
                    <@spring.formHiddenInput "productForm.number"/>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Название</label>
                        <div class="col-sm-9">
                            <@spring.formInput "productForm.name" "class='form-control' required" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Описание</label>
                        <div class="col-sm-9">
                            <@spring.formInput "productForm.description" "class='form-control'" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Цена</label>
                        <div class="col-sm-9">
                            <@spring.formInput "productForm.price" "class='form-control' min='0.1' step='0.1''" "number"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Скидка</label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "productForm.productDiscount", productDiscountMap, "class='form-control'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Количество</label>
                        <div class="col-sm-9">
                            <@spring.formInput "productForm.amount" "class='form-control' min='0' step='1'" "number"/>
                        </div>
                    </div>

                </li>
                <li class="list-group-item text-right my-form-group-footer">
                    <input type="submit" value="Сохранить"  class="btn btn-sm btn-outline-secondary"/>
                </li>
            </ul>
        </form>
    </div>

</@c.page>