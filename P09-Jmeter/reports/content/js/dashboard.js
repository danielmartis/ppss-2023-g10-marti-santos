/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [1.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Reptiles"], "isController": false}, {"data": [1.0, 500, 1500, "Main Catalog"], "isController": false}, {"data": [1.0, 500, 1500, "Birds"], "isController": false}, {"data": [1.0, 500, 1500, "Login"], "isController": false}, {"data": [1.0, 500, 1500, "Login-0"], "isController": false}, {"data": [1.0, 500, 1500, "Login-1"], "isController": false}, {"data": [1.0, 500, 1500, "Home-0"], "isController": false}, {"data": [1.0, 500, 1500, "Home-1"], "isController": false}, {"data": [1.0, 500, 1500, "Dogs"], "isController": false}, {"data": [1.0, 500, 1500, "Sign In"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out-0"], "isController": false}, {"data": [1.0, 500, 1500, "Home"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out-1"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1333, 0, 0.0, 1.3398349587396838, 0, 6, 1.0, 2.0, 3.0, 4.0, 22.49523263074405, 68.788781019542, 11.49434019925916], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Reptiles", 94, 0, 0.0, 1.3936170212765964, 0, 2, 1.0, 2.0, 2.0, 2.0, 1.8552509522963665, 6.725284702074328, 0.4565656640416839], "isController": false}, {"data": ["Main Catalog", 100, 0, 0.0, 1.2200000000000002, 1, 3, 1.0, 2.0, 2.0, 2.989999999999995, 1.893186422066981, 9.363322885310767, 0.37114958420893207], "isController": false}, {"data": ["Birds", 90, 0, 0.0, 1.477777777777778, 1, 3, 1.0, 2.0, 2.0, 3.0, 1.8425254882692546, 6.675556212381772, 0.44803598298734804], "isController": false}, {"data": ["Login", 98, 0, 0.0, 2.642857142857143, 1, 6, 3.0, 3.1000000000000085, 4.0, 6.0, 1.8745935192624048, 9.632920994490991, 2.755139889150312], "isController": false}, {"data": ["Login-0", 98, 0, 0.0, 1.581632653061224, 0, 5, 2.0, 2.0, 2.0, 5.0, 1.8746652383502946, 0.35333045996250667, 1.6110404392072843], "isController": false}, {"data": ["Login-1", 98, 0, 0.0, 0.9795918367346936, 0, 3, 1.0, 1.0, 2.0, 3.0, 1.874629378120397, 9.279781560246379, 1.1441829700441877], "isController": false}, {"data": ["Home-0", 104, 0, 0.0, 0.5576923076923079, 0, 2, 1.0, 1.0, 1.0, 2.0, 1.7595804077489214, 0.2508776753235767, 0.30657503066576436], "isController": false}, {"data": ["Home-1", 104, 0, 0.0, 0.4519230769230769, 0, 2, 0.0, 1.0, 1.0, 2.0, 1.7595804077489214, 2.3214776668640558, 0.30829337090770664], "isController": false}, {"data": ["Dogs", 82, 0, 0.0, 1.5609756097560976, 1, 6, 1.0, 2.0, 2.0, 6.0, 1.7928199746381563, 7.16077509401373, 0.43419858760767854], "isController": false}, {"data": ["Sign In", 100, 0, 0.0, 1.5500000000000003, 1, 5, 1.0, 2.0, 2.9499999999999886, 4.97999999999999, 1.8914318138831097, 7.24599400652544, 1.0066702525061473], "isController": false}, {"data": ["Sign Out", 87, 0, 0.0, 2.275862068965517, 1, 5, 2.0, 3.0, 3.0, 5.0, 1.8143143143143143, 9.31076340011887, 1.9046756717655156], "isController": false}, {"data": ["Sign Out-0", 87, 0, 0.0, 1.2298850574712643, 0, 3, 1.0, 2.0, 2.0, 3.0, 1.8143899895724713, 0.5900311196558915, 0.9603509515119916], "isController": false}, {"data": ["Home", 104, 0, 0.0, 1.1153846153846156, 0, 3, 1.0, 2.0, 2.0, 3.0, 1.7595506378371062, 2.5723118211349103, 0.6148579987649307], "isController": false}, {"data": ["Sign Out-1", 87, 0, 0.0, 0.9310344827586209, 0, 4, 1.0, 1.0, 1.0, 4.0, 1.8143521511543033, 8.720938757794414, 0.9443844693019958], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1333, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
