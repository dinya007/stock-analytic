import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PlotComponent} from './plot/plot.component';
import {MainComponent} from './main/main.component';
import {NewPlotComponent} from './new-plot/new.plot.component';


const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'plot', component: PlotComponent},
  {path: 'new-plot', component: NewPlotComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}
