<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as c/>
<@c.page title="CMS / Добавить скидку клиента">

    <div class="row h-100 justify-content-center align-items-center">
        <form name="person" action=""  method="post">
            <ul class="list-group shadow-sm rounded" style="width: 600px;">
                <li class="list-group-item p-0">
                    <div class="d-flex flex-row">
                        <div class="my-form-group-header-icon" style="background-image: url(/images/icons48/add.png);"></div>
                        <div class="my-form-group-header-title">Добавить скидку клиента</div>
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

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Название</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerDiscountForm.name" "class='form-control' required" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Процент скидки</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerDiscountForm.value" "class='form-control' min='0' max='20' step='1'" "number"/>
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
