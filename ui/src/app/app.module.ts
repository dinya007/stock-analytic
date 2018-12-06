import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ChartsModule} from 'ng2-charts';


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app.routing.module';
import {PlotComponent} from './plot/plot.component';
import {MainComponent} from './main/main.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {RestService} from './service/rest.service';
import {NewPlotComponent} from './new-plot/new.plot.component';
import {PlotlyModule} from 'angular-plotly.js';
import {InstrumentService} from './service/instrument.service';
import {PlotService} from './service/plot.service';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    PlotComponent,
    NewPlotComponent
  ],
  imports: [
    BrowserModule,
    ChartsModule,
    AppRoutingModule,
    HttpClientModule,
    PlotlyModule
  ],
  providers: [
    RestService,
    InstrumentService,
    PlotService,
    HttpClient
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
