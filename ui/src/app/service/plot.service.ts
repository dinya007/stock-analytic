import {Injectable} from '@angular/core';
import {RestService} from './rest.service';
import {Observable} from 'rxjs/Observable';
import {Plot} from '../dto/plot';

@Injectable()
export class PlotService {

  constructor(private restService: RestService) {

  }

  public getPlot(securityId: String): Observable<Array<Plot>> {
    return this.restService.getArray<Plot>('/api/plots?securityId=' + securityId);
  }

}
