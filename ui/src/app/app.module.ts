import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ChartsModule} from 'ng2-charts';


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app.routing.module';
import {PlotComponent} from './plot/plot.component';
import {MainComponent} from './main/main.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {RestService} from './service/rest.service';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    PlotComponent
  ],
  imports: [
    BrowserModule,
    ChartsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    RestService,
    HttpClient
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
