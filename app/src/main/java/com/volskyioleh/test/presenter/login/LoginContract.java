/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.volskyioleh.test.presenter.login;

import com.volskyioleh.test.api.models.LoginModel;
import com.volskyioleh.test.presenter.base.BaseContract;


public interface LoginContract {

    interface View extends BaseContract.View {
  //    void  showList(LiveData<List<ArticleModel>> articles);
        void getResult(String key);
        void showToast(String message);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void login(LoginModel loginModel);
    }
}
