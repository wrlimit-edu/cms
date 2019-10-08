<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as c/>
<@c.page title="CMS / Добавить клиента">

    <div class="row h-100 justify-content-center align-items-center">
        <form name="person"  action="/person/add"  method="post">
            <ul class="list-group shadow-sm rounded" style="width: 600px;">
                <li class="list-group-item p-0">
                    <div class="d-flex flex-row">
                        <div class="my-form-group-header-icon" style="background-image: url(/images/icons48/add.png);"></div>
                        <div class="my-form-group-header-title">Добавить клиента</div>
                    </div>
                </li>
                <li class="list-group-item my-form-group-body">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Фамилия</label>
                        <div class="col-sm-9">
                            <@spring.formInput "personForm.lastName" "class='form-control'" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Имя</label>
                        <div class="col-sm-9">
                            <@spring.formInput "personForm.firstName" "class='form-control'" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Отчество</label>
                        <div class="col-sm-9">
                            <@spring.formInput "personForm.middleName" "class='form-control'" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col-sm-9">
                            <@spring.formInput "personForm.phone" "class='form-control'" "text"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Адрес</label>
                        <div class="col-sm-9">
                            <@spring.formInput "personForm.address" "class='form-control'" "text"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Скидка</label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "personForm.discount", discountMap, "class='form-control'"/>
                        </div>
                    </div>
                    <#--
                   <div class="form-group row">
                       <label for="gender" class="col-sm-3 col-form-label">Пол</label>
                       <div class="col-sm-9" style="padding-top: 6px;">
                           <div class="form-check form-check-inline" style="padding-right: 50px">
                               <input name="gender" id="inlineRadio1" class="form-check-input" type="radio" value="true" checked>
                               <label for="inlineRadio1" class="form-check-label">Мужской</label>
                           </div>
                           <div class="form-check form-check-inline">
                               <input name="gender" id="inlineRadio2" class="form-check-input" type="radio" value="false">
                               <label for="inlineRadio2" class="form-check-label">Женский</label>
                           </div>
                       </div>
                   </div>
                   -->
                </li>
                <li class="list-group-item text-right my-form-group-footer">
                    <input type="submit" value="Сохранить"  class="btn btn-sm btn-outline-secondary"/>
                </li>
            </ul>
        </form>
    </div>

</@c.page>
