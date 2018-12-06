import {Component, OnInit} from '@angular/core';
import {PlotService} from '../service/plot.service';
import {InstrumentService} from '../service/instrument.service';


@Component({
  templateUrl: './new.plot.component.html'
})
export class NewPlotComponent implements OnInit {

  public instruments: Array<String> = [];
  public graph = this.initGraph();
  private colors: Array<String> = ['green', 'blue', 'red'];

  constructor(private plotService: PlotService, private  instrumentService: InstrumentService) {
  }

  ngOnInit(): void {
    this.instrumentService.getAllInstruments().subscribe(instruments => {
      this.instruments = instruments;
    });
  }

  public relayout(event: any) {
    const startDate = event['xaxis.range[0]'];
    const endDate = event['xaxis.range[1]'];

    let max = Number.MIN_SAFE_INTEGER;
    let min = Number.MAX_SAFE_INTEGER;

    for (const plot of this.graph.data) {
      for (let i = plot.x.length - 1; i >= 0; i--) {
        const point = plot.y[i];
        min = Math.min(min, point);
        if (min === 1) {
          console.log('min');
        }
        max = Math.max(max, point);
        if (plot.x[i] === startDate) {
          break;
        }
      }
    }

    // const minRange = min - min * 0.01;
    const minRange = min;
    // const maxRange = max + max * 0.01;
    const maxRange = max;
    this.graph.layout.yaxis = {
      autorange: false,
      fixedrange: true,
      range: [minRange, maxRange],
      tickmode: 'linear',
      tick0: minRange,
      dtick: (maxRange - minRange) / 5
    };
  }

  public drawPlot(securityId: any) {
    this.graph = this.initGraph();
    this.plotService.getPlot(securityId).subscribe(plots => {
      for (let i = 0; i < plots.length; i++) {
        const plot = plots[i];
        const data = {x: [], y: [], type: 'scatter', mode: 'lines+points', marker: {color: this.colors[i]}, name: plot.name};
        const x = [];
        const y = [];
        for (const point of plot.points) {
          x.push(point.date);
          y.push(point.value);
        }
        data.x = x;
        data.y = y;
        this.graph.data.push(data);
      }
      this.graph.layout.title = securityId;
    });
  }

  private initGraph() {
    return {
      data: [],
      layout: {
        title: '',
        autosize: true,
        xaxis: {
          autorange: true,
          type: 'date',
          rangeselector: {
            buttons: [
              {
                count: 1,
                label: '1d',
                step: 'day',
                stepmode: 'backward'
              },
              {
                count: 7,
                label: '1w',
                step: 'day',
                stepmode: 'backward'
              },
              {
                count: 1,
                label: '1m',
                step: 'month',
                stepmode: 'backward'
              },
              {
                count: 6,
                label: '6m',
                step: 'month',
                stepmode: 'backward'
              },
              {
                count: 1,
                label: '1y',
                step: 'year',
                stepmode: 'backward'
              },
              {
                count: 3,
                label: '3y',
                step: 'year',
                stepmode: 'backward'
              },
              {
                count: 10,
                label: '10y',
                step: 'year',
                stepmode: 'backward'
              },
              {step: 'all'}
            ]
          }
        },
        yaxis: {}
      }
    };
  }

}
