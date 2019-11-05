<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as c/>
<@c.page title="CMS / Редактировать клиента">

    <div class="row h-100 justify-content-center align-items-center">
        <form name="person" action="/customer/update"  method="post">
            <ul class="list-group shadow-sm rounded" style="width: 600px;">
                <li class="list-group-item p-0">
                    <div class="d-flex flex-row">
                        <div class="my-form-group-header-icon" style="background-image: url(/images/icons48/edit.png);"></div>
                        <div class="my-form-group-header-title">Редактировать клиента</div>
                    </div>
                </li>
                <li class="list-group-item my-form-group-body">
                    <@spring.formHiddenInput "customerForm.id"/>
                    <@spring.formHiddenInput "customerForm.person.id"/>
                    <@spring.formHiddenInput "customerForm.number"/>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Фамилия</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerForm.person.lastName" "class='form-control' required" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Имя</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerForm.person.firstName" "class='form-control' required" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Отчество</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerForm.person.middleName" "class='form-control'" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Пол</label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "customerForm.person.gender", genderMap, "class='form-control'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerForm.phone" "class='form-control' required" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Адрес</label>
                        <div class="col-sm-9">
                            <@spring.formInput "customerForm.address" "class='form-control' required" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Скидка</label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "customerForm.customerDiscount", customerDiscountMap, "class='form-control'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Статус</label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "customerForm.enabled", enabledMap, "class='form-control'"/>
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
